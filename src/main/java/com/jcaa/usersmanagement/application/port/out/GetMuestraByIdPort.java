package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.MuestraModel;
import com.jcaa.usersmanagement.domain.valueobject.MuestraId;
import java.util.Optional;

public interface GetMuestraByIdPort {
  Optional<MuestraModel> getById(MuestraId muestraId);
}
