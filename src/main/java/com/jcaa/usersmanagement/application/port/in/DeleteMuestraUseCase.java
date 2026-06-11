package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DeleteMuestraCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteMuestraUseCase {
  void execute(@NotNull @Valid DeleteMuestraCommand command);
}
