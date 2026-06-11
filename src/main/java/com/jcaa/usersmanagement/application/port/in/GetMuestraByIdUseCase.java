package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetMuestraByIdQuery;
import com.jcaa.usersmanagement.domain.model.MuestraModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetMuestraByIdUseCase {
  MuestraModel execute(@NotNull @Valid GetMuestraByIdQuery query);
}
