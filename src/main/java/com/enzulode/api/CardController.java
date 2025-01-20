package com.enzulode.api;

import com.enzulode.dto.CreateCardDto;
import com.enzulode.dto.ReadCardDto;
import com.enzulode.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/card")
@RequiredArgsConstructor
@Slf4j
public class CardController {

  private final CardService cardService;

  @PostMapping("/add")
  @PreAuthorize("hasRole('ADMIN')")
  public ReadCardDto addCard(@RequestBody CreateCardDto dto) {
    return cardService.createCard(dto);
  }

  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public List<ReadCardDto> getAllCards() {
    return cardService.findAllCards();
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void deleteCard(@PathVariable Long id) {
    cardService.deleteCardById(id);
  }

  @PostMapping("/check")
  public void checkCard(@RequestBody byte[] uidBytes) {
    cardService.checkByCardId(UUID.nameUUIDFromBytes(uidBytes));
  }
}
