package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteMuestraUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteMuestraPort;
import com.jcaa.usersmanagement.application.port.out.GetMuestraByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteMuestraCommand;
import com.jcaa.usersmanagement.application.service.mapper.MuestraApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.MuestraNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.MuestraId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class DeleteMuestraService implements DeleteMuestraUseCase {

  private final DeleteMuestraPort deleteMuestraPort;
  private final GetMuestraByIdPort getMuestraByIdPort;
  private final Validator validator;

  @Override
  public void execute(final DeleteMuestraCommand command) {
    validateCommand(command);

    final MuestraId muestraId = MuestraApplicationMapper.fromDeleteCommandToMuestraId(command);
    ensureMuestraExists(muestraId);
    deleteMuestraPort.delete(muestraId);
  }

  private void validateCommand(final DeleteMuestraCommand command) {
    final Set<ConstraintViolation<DeleteMuestraCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureMuestraExists(final MuestraId muestraId) {
    getMuestraByIdPort
        .getById(muestraId)
        .orElseThrow(() -> MuestraNotFoundException.becauseIdWasNotFound(muestraId.value()));
  }
}
