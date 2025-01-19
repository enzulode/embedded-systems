package com.enzulode.exception;

import java.io.Serial;

public class CardNotFoundException extends RuntimeException {

  @Serial private final static long serialVersionUID = -631783678L;

  public CardNotFoundException(String message) {
    super(message);
  }

  public CardNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
