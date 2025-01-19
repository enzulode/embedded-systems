package com.enzulode.service.impl;

import com.enzulode.dao.entity.Card;
import com.enzulode.dao.entity.Event;
import com.enzulode.dao.repository.CardRepository;
import com.enzulode.dao.repository.EventRepository;
import com.enzulode.dto.CreateCardDto;
import com.enzulode.dto.ReadCardDto;
import com.enzulode.dto.mapper.CardMapper;
import com.enzulode.dto.util.EventType;
import com.enzulode.exception.CardCheckFailureException;
import com.enzulode.exception.CardNotFoundException;
import com.enzulode.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

  private final CardMapper mapper;
  private final CardRepository cardRepository;
  private final EventRepository eventRepository;

  @Override
  @Transactional
  public ReadCardDto createCard(CreateCardDto dto) {
    var createdCard = cardRepository.save(mapper.toEntity(dto));
    return mapper.toDto(createdCard);
  }

  @Override
  public List<ReadCardDto> findAllCards() {
    List<Card> cards = cardRepository.findAll();
    return cards.stream().map(mapper::toDto).toList();
  }

  @Override
  public ReadCardDto findCardById(Long id) {
    var card = cardRepository.findById(id)
        .orElseThrow(() -> new CardNotFoundException("Card not found"));
    return mapper.toDto(card);
  }

  @Override
  @Transactional
  public void deleteCardById(Long id) {
    cardRepository.deleteById(id);
  }

  @Override
  @Transactional(noRollbackFor = CardCheckFailureException.class)
  public void checkByCardId(UUID cardId) {
    var card = cardRepository.findByCardId(cardId);
    if (card.isEmpty()) {
      var deniedEvent = new Event(cardId, "UNKNOWN", EventType.DENIED);
      eventRepository.save(deniedEvent);
      throw new CardCheckFailureException("Card was denied");
    }

    var latestEvent = eventRepository.findLatestEventFor(cardId);
    if (latestEvent.isPresent() && latestEvent.get().getType() == EventType.BEGIN) {
      var endEvent = new Event(cardId, card.get().getUsername(), EventType.END);
      eventRepository.save(endEvent);
      return;
    }

    var beginEvent = new Event(cardId, card.get().getUsername(), EventType.BEGIN);
    eventRepository.save(beginEvent);
  }
}
