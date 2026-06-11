package com.jcaa.usersmanagement.domain.exception;

public final class InvalidMuestraDescripcionException extends DomainException {

  private static final String MESSAGE_EMPTY = "The muestra descripcion must not be empty.";
  private static final String MESSAGE_TOO_SHORT =
      "The muestra descripcion must have at least %d characters.";

  private InvalidMuestraDescripcionException(final String message) {
    super(message);
  }

  public static InvalidMuestraDescripcionException becauseValueIsEmpty() {
    return new InvalidMuestraDescripcionException(MESSAGE_EMPTY);
  }

  public static InvalidMuestraDescripcionException becauseLengthIsTooShort(
      final int minimumLength) {
    return new InvalidMuestraDescripcionException(String.format(MESSAGE_TOO_SHORT, minimumLength));
  }
}
