package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateMuestraUseCase;
import com.jcaa.usersmanagement.application.port.out.GetMuestraByCodigoPort;
import com.jcaa.usersmanagement.application.port.out.GetMuestraByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateMuestraPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateMuestraCommand;
import com.jcaa.usersmanagement.application.service.mapper.MuestraApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.MuestraAlreadyExistsException;
import com.jcaa.usersmanagement.domain.exception.MuestraNotFoundException;
import com.jcaa.usersmanagement.domain.model.MuestraModel;
import com.jcaa.usersmanagement.domain.valueobject.MuestraCodigo;
import com.jcaa.usersmanagement.domain.valueobject.MuestraId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class UpdateMuestraService implements UpdateMuestraUseCase {

  private final UpdateMuestraPort updateMuestraPort;
  private final GetMuestraByIdPort getMuestraByIdPort;
  private final GetMuestraByCodigoPort getMuestraByCodigoPort;
  private final Validator validator;

  @Override
  public MuestraModel execute(final UpdateMuestraCommand command) {
    validateCommand(command);

    final MuestraId muestraId = new MuestraId(command.id());
    findExistingMuestraOrFail(muestraId);
    final MuestraCodigo newCodigo = new MuestraCodigo(command.codigo());

    ensureCodigoIsNotTakenByAnotherMuestra(newCodigo, muestraId);

    final MuestraModel muestraToUpdate = MuestraApplicationMapper.fromUpdateCommandToModel(command);
    return updateMuestraPort.update(muestraToUpdate);
  }

  private void validateCommand(final UpdateMuestraCommand command) {
    final Set<ConstraintViolation<UpdateMuestraCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private MuestraModel findExistingMuestraOrFail(final MuestraId muestraId) {
    return getMuestraByIdPort
        .getById(muestraId)
        .orElseThrow(() -> MuestraNotFoundException.becauseIdWasNotFound(muestraId.value()));
  }

  private void ensureCodigoIsNotTakenByAnotherMuestra(
      final MuestraCodigo newCodigo, final MuestraId ownerId) {
    getMuestraByCodigoPort
        .getByCodigo(newCodigo)
        .ifPresent(
            found -> {
              if (!found.getId().equals(ownerId)) {
                throw MuestraAlreadyExistsException.becauseCodigoAlreadyExists(newCodigo.value());
              }
            });
  }
}
