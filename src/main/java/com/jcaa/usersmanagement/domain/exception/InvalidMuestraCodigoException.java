package com.jcaa.usersmanagement.domain.exception;

public final class InvalidMuestraCodigoException extends DomainException {

  private static final String MESSAGE_EMPTY = "The muestra codigo must not be empty.";
  private static final String MESSAGE_INVALID_FORMAT =
      "The muestra codigo format is invalid: '%s'.";

  private InvalidMuestraCodigoException(final String message) {
    super(message);
  }

  public static InvalidMuestraCodigoException becauseValueIsEmpty() {
    return new InvalidMuestraCodigoException(MESSAGE_EMPTY);
  }

  public static InvalidMuestraCodigoException becauseFormatIsInvalid(final String codigo) {
    return new InvalidMuestraCodigoException(String.format(MESSAGE_INVALID_FORMAT, codigo));
  }
}
