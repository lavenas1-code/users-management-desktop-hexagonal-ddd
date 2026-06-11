package com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception;

public final class PersistenceException extends RuntimeException {

  private static final String MESSAGE_SAVE = "Failed to save user with ID: '%s'.";
  private static final String MESSAGE_UPDATE = "Failed to update user with ID: '%s'.";
  private static final String MESSAGE_FIND = "Failed to find user with ID: '%s'.";
  private static final String MESSAGE_EMAIL = "Failed to find user with email: '%s'.";
  private static final String MESSAGE_ALL = "Failed to retrieve all users.";
  private static final String MESSAGE_DELETE = "Failed to delete user with ID: '%s'.";
  private static final String MESSAGE_CONNECTION = "Could not establish database connection.";

  private static final String MESSAGE_MUESTRA_SAVE = "Failed to save muestra with ID: '%s'.";
  private static final String MESSAGE_MUESTRA_UPDATE = "Failed to update muestra with ID: '%s'.";
  private static final String MESSAGE_MUESTRA_FIND = "Failed to find muestra with ID: '%s'.";
  private static final String MESSAGE_MUESTRA_CODIGO =
      "Failed to find muestra with codigo: '%s'.";
  private static final String MESSAGE_MUESTRA_ALL = "Failed to retrieve all muestras.";
  private static final String MESSAGE_MUESTRA_DELETE = "Failed to delete muestra with ID: '%s'.";

  private PersistenceException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public static PersistenceException becauseSaveFailed(final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_SAVE, userId), cause);
  }

  public static PersistenceException becauseUpdateFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_UPDATE, userId), cause);
  }

  public static PersistenceException becauseFindByIdFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_FIND, userId), cause);
  }

  public static PersistenceException becauseFindByEmailFailed(
      final String email, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_EMAIL, email), cause);
  }

  public static PersistenceException becauseFindAllFailed(final Throwable cause) {
    return new PersistenceException(MESSAGE_ALL, cause);
  }

  public static PersistenceException becauseDeleteFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_DELETE, userId), cause);
  }

  public static PersistenceException becauseConnectionFailed(final Throwable cause) {
    return new PersistenceException(MESSAGE_CONNECTION, cause);
  }

  public static PersistenceException becauseMuestraSaveFailed(
      final String muestraId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_MUESTRA_SAVE, muestraId), cause);
  }

  public static PersistenceException becauseMuestraUpdateFailed(
      final String muestraId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_MUESTRA_UPDATE, muestraId), cause);
  }

  public static PersistenceException becauseMuestraFindByIdFailed(
      final String muestraId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_MUESTRA_FIND, muestraId), cause);
  }

  public static PersistenceException becauseMuestraFindByCodigoFailed(
      final String codigo, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_MUESTRA_CODIGO, codigo), cause);
  }

  public static PersistenceException becauseMuestraFindAllFailed(final Throwable cause) {
    return new PersistenceException(MESSAGE_MUESTRA_ALL, cause);
  }

  public static PersistenceException becauseMuestraDeleteFailed(
      final String muestraId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_MUESTRA_DELETE, muestraId), cause);
  }
}
