package com.jcaa.usersmanagement.domain.exception;

public final class MuestraNotFoundException extends DomainException {

  private static final String MESSAGE_BY_ID = "The muestra with id '%s' was not found.";

  private MuestraNotFoundException(final String message) {
    super(message);
  }

  public static MuestraNotFoundException becauseIdWasNotFound(final String muestraId) {
    return new MuestraNotFoundException(String.format(MESSAGE_BY_ID, muestraId));
  }
}
