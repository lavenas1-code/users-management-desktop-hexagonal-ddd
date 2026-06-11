package com.jcaa.usersmanagement.domain.exception;

public final class InvalidMuestraEstadoException extends DomainException {

  private static final String MESSAGE_INVALID = "The muestra estado '%s' is not valid.";

  private InvalidMuestraEstadoException(final String message) {
    super(message);
  }

  public static InvalidMuestraEstadoException becauseValueIsInvalid(final String estado) {
    return new InvalidMuestraEstadoException(String.format(MESSAGE_INVALID, estado));
  }
}
