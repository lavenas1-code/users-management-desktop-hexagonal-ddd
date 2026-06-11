package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllMuestrasUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllMuestrasPort;
import com.jcaa.usersmanagement.domain.model.MuestraModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class GetAllMuestrasService implements GetAllMuestrasUseCase {

  private final GetAllMuestrasPort getAllMuestrasPort;

  @Override
  public List<MuestraModel> execute() {
    return getAllMuestrasPort.getAll();
  }
}
