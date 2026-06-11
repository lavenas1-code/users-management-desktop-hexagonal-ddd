package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateMuestraCommand;
import com.jcaa.usersmanagement.domain.model.MuestraModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateMuestraUseCase {
  MuestraModel execute(@NotNull @Valid UpdateMuestraCommand command);
}
