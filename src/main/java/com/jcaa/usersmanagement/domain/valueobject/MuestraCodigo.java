package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidMuestraCodigoException;
import java.util.Objects;
import java.util.regex.Pattern;

public record MuestraCodigo(String value) {

  private static final Pattern CODIGO_PATTERN = Pattern.compile("^[A-Z0-9-]{3,20}$");

  public MuestraCodigo {
    final String normalizedValue =
        Objects.requireNonNull(value, "MuestraCodigo cannot be null").trim().toUpperCase();
    validateNotEmpty(normalizedValue);
    validateFormat(normalizedValue);
    value = normalizedValue;
  }

  private static void validateNotEmpty(final String normalizedValue) {
    if (normalizedValue.isEmpty()) {
      throw InvalidMuestraCodigoException.becauseValueIsEmpty();
    }
  }

  private static void validateFormat(final String normalizedValue) {
    if (!CODIGO_PATTERN.matcher(normalizedValue).matches()) {
      throw InvalidMuestraCodigoException.becauseFormatIsInvalid(normalizedValue);
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
