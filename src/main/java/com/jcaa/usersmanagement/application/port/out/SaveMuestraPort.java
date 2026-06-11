package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.MuestraModel;

public interface SaveMuestraPort {
  MuestraModel save(MuestraModel muestra);
}
