package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.InvalidMuestraEstadoException;

public enum MuestraEstado {
  DISPONIBLE,
  EN_ANALISIS,
  AGOTADA;

  public static MuestraEstado fromString(final String value) {
    for (final MuestraEstado estado : values()) {
      if (estado.name().equalsIgnoreCase(value)) {
        return estado;
      }
    }
    throw InvalidMuestraEstadoException.becauseValueIsInvalid(value);
  }
}
