package com.enzulode.exception;

import java.io.Serial;

public class CardCheckFailureException extends RuntimeException {

  @Serial private static final long serialVersionUID = 612371637L;

  public CardCheckFailureException(String message) {
    super(message);
  }

  public CardCheckFailureException(String message, Throwable cause) {
    super(message, cause);
  }
}
