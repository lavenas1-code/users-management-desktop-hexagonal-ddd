package com.jcaa.usersmanagement.domain.exception;

public final class MuestraAlreadyExistsException extends DomainException {

  private static final String MESSAGE_CODIGO_EXISTS = "A muestra with codigo '%s' already exists.";

  private MuestraAlreadyExistsException(final String message) {
    super(message);
  }

  public static MuestraAlreadyExistsException becauseCodigoAlreadyExists(final String codigo) {
    return new MuestraAlreadyExistsException(String.format(MESSAGE_CODIGO_EXISTS, codigo));
  }
}
