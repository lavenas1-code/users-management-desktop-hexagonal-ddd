package com.jcaa.usersmanagement.domain.exception;

public final class InvalidLoteIdException extends DomainException {

  private static final String MESSAGE_EMPTY = "The lote id must not be empty.";

  private InvalidLoteIdException(final String message) {
    super(message);
  }

  public static InvalidLoteIdException becauseValueIsEmpty() {
    return new InvalidLoteIdException(MESSAGE_EMPTY);
  }
}
