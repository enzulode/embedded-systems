package com.enzulode.service;

import com.enzulode.dto.CreateCardDto;
import com.enzulode.dto.ReadCardDto;

import java.util.List;
import java.util.UUID;

public interface CardService {

  ReadCardDto createCard(CreateCardDto dto);

  List<ReadCardDto> findAllCards();

  ReadCardDto findCardById(Long id);

  void deleteCardById(Long id);

  void checkByCardId(UUID cardId);
}
