package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.valueobject.MuestraId;

public interface DeleteMuestraPort {
  void delete(MuestraId muestraId);
}
