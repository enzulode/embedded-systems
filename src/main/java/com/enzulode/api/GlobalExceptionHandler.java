package com.enzulode.api;

import com.enzulode.exception.CardCheckFailureException;
import com.enzulode.exception.CardNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler({ CardCheckFailureException.class })
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public void handleCardCheckFailureException(CardCheckFailureException e) {
    log.warn("Card was denied");
  }

  @ExceptionHandler({ CardNotFoundException.class })
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public void handleCardNotFoundException(CardNotFoundException e) {
    log.warn("Card not found");
  }

  @ExceptionHandler({ DataIntegrityViolationException.class })
  public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
    log.warn("Data integrity violation", e);
    return ResponseEntity.status(HttpStatus.CONFLICT).build();
  }
}
