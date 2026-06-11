package com.jcaa.usersmanagement.domain.exception;

public final class InvalidMuestraIdException extends DomainException {

  private static final String MESSAGE_EMPTY = "The muestra id must not be empty.";

  private InvalidMuestraIdException(final String message) {
    super(message);
  }

  public static InvalidMuestraIdException becauseValueIsEmpty() {
    return new InvalidMuestraIdException(MESSAGE_EMPTY);
  }
}
