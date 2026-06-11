package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.MuestraModel;
import com.jcaa.usersmanagement.domain.valueobject.MuestraCodigo;
import java.util.Optional;

public interface GetMuestraByCodigoPort {
  Optional<MuestraModel> getByCodigo(MuestraCodigo codigo);
}
