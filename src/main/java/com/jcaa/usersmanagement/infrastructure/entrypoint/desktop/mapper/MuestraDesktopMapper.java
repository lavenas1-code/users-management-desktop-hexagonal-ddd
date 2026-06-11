package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateMuestraCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteMuestraCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateMuestraCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetMuestraByIdQuery;
import com.jcaa.usersmanagement.domain.model.MuestraModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateMuestraRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.MuestraResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateMuestraRequest;

import java.util.List;

public final class MuestraDesktopMapper {

  private MuestraDesktopMapper() {}

  public static CreateMuestraCommand toCreateCommand(final CreateMuestraRequest request) {
    return new CreateMuestraCommand(
        request.id(), request.codigo(), request.loteId(), request.descripcion());
  }

  public static UpdateMuestraCommand toUpdateCommand(final UpdateMuestraRequest request) {
    return new UpdateMuestraCommand(
        request.id(),
        request.codigo(),
        request.loteId(),
        request.descripcion(),
        request.estado());
  }

  public static DeleteMuestraCommand toDeleteCommand(final String id) {
    return new DeleteMuestraCommand(id);
  }

  public static GetMuestraByIdQuery toGetByIdQuery(final String id) {
    return new GetMuestraByIdQuery(id);
  }

  public static MuestraResponse toResponse(final MuestraModel muestra) {
    return new MuestraResponse(
        muestra.getId().value(),
        muestra.getCodigo().value(),
        muestra.getLoteId().value(),
        muestra.getDescripcion().value(),
        muestra.getEstado().name());
  }

  public static List<MuestraResponse> toResponseList(final List<MuestraModel> muestras) {
    return muestras.stream().map(MuestraDesktopMapper::toResponse).toList();
  }
}
