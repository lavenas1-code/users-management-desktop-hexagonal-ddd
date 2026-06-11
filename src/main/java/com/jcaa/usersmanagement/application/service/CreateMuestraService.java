package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateMuestraUseCase;
import com.jcaa.usersmanagement.application.port.out.GetMuestraByCodigoPort;
import com.jcaa.usersmanagement.application.port.out.SaveMuestraPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateMuestraCommand;
import com.jcaa.usersmanagement.application.service.mapper.MuestraApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.MuestraAlreadyExistsException;
import com.jcaa.usersmanagement.domain.model.MuestraModel;
import com.jcaa.usersmanagement.domain.valueobject.MuestraCodigo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class CreateMuestraService implements CreateMuestraUseCase {

  private final SaveMuestraPort saveMuestraPort;
  private final GetMuestraByCodigoPort getMuestraByCodigoPort;
  private final Validator validator;

  @Override
  public MuestraModel execute(final CreateMuestraCommand command) {
    validateCommand(command);

    final MuestraCodigo codigo = new MuestraCodigo(command.codigo());
    ensureCodigoIsNotTaken(codigo);

    final MuestraModel muestraToSave = MuestraApplicationMapper.fromCreateCommandToModel(command);
    return saveMuestraPort.save(muestraToSave);
  }

  private void validateCommand(final CreateMuestraCommand command) {
    final Set<ConstraintViolation<CreateMuestraCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureCodigoIsNotTaken(final MuestraCodigo codigo) {
    getMuestraByCodigoPort
        .getByCodigo(codigo)
        .ifPresent(
            ignored -> {
              throw MuestraAlreadyExistsException.becauseCodigoAlreadyExists(codigo.value());
            });
  }
}
