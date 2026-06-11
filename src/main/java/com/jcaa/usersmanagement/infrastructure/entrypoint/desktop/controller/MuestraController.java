package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateMuestraUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteMuestraUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllMuestrasUseCase;
import com.jcaa.usersmanagement.application.port.in.GetMuestraByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateMuestraUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateMuestraRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.MuestraResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateMuestraRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.MuestraDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class MuestraController {

  private final CreateMuestraUseCase createMuestraUseCase;
  private final UpdateMuestraUseCase updateMuestraUseCase;
  private final DeleteMuestraUseCase deleteMuestraUseCase;
  private final GetMuestraByIdUseCase getMuestraByIdUseCase;
  private final GetAllMuestrasUseCase getAllMuestrasUseCase;

  public List<MuestraResponse> listAllMuestras() {
    final var muestras = getAllMuestrasUseCase.execute();
    return MuestraDesktopMapper.toResponseList(muestras);
  }

  public MuestraResponse findMuestraById(final String id) {
    final var query = MuestraDesktopMapper.toGetByIdQuery(id);
    final var muestra = getMuestraByIdUseCase.execute(query);
    return MuestraDesktopMapper.toResponse(muestra);
  }

  public MuestraResponse createMuestra(final CreateMuestraRequest request) {
    final var command = MuestraDesktopMapper.toCreateCommand(request);
    final var muestra = createMuestraUseCase.execute(command);
    return MuestraDesktopMapper.toResponse(muestra);
  }

  public MuestraResponse updateMuestra(final UpdateMuestraRequest request) {
    final var command = MuestraDesktopMapper.toUpdateCommand(request);
    final var muestra = updateMuestraUseCase.execute(command);
    return MuestraDesktopMapper.toResponse(muestra);
  }

  public void deleteMuestra(final String id) {
    final var command = MuestraDesktopMapper.toDeleteCommand(id);
    deleteMuestraUseCase.execute(command);
  }
}
