package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetMuestraByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetMuestraByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetMuestraByIdQuery;
import com.jcaa.usersmanagement.application.service.mapper.MuestraApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.MuestraNotFoundException;
import com.jcaa.usersmanagement.domain.model.MuestraModel;
import com.jcaa.usersmanagement.domain.valueobject.MuestraId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class GetMuestraByIdService implements GetMuestraByIdUseCase {

  private final GetMuestraByIdPort getMuestraByIdPort;
  private final Validator validator;

  @Override
  public MuestraModel execute(final GetMuestraByIdQuery query) {
    validateQuery(query);

    final MuestraId muestraId = MuestraApplicationMapper.fromGetMuestraByIdQueryToMuestraId(query);
    return getMuestraByIdPort
        .getById(muestraId)
        .orElseThrow(() -> MuestraNotFoundException.becauseIdWasNotFound(muestraId.value()));
  }

  private void validateQuery(final GetMuestraByIdQuery query) {
    final Set<ConstraintViolation<GetMuestraByIdQuery>> violations = validator.validate(query);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
