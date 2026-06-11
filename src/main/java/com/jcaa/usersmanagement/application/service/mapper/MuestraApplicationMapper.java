package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateMuestraCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteMuestraCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateMuestraCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetMuestraByIdQuery;
import com.jcaa.usersmanagement.domain.enums.MuestraEstado;
import com.jcaa.usersmanagement.domain.model.MuestraModel;
import com.jcaa.usersmanagement.domain.valueobject.LoteId;
import com.jcaa.usersmanagement.domain.valueobject.MuestraCodigo;
import com.jcaa.usersmanagement.domain.valueobject.MuestraDescripcion;
import com.jcaa.usersmanagement.domain.valueobject.MuestraId;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MuestraApplicationMapper {

  public MuestraModel fromCreateCommandToModel(final CreateMuestraCommand command) {
    return MuestraModel.create(
        new MuestraId(command.id()),
        new MuestraCodigo(command.codigo()),
        new LoteId(command.loteId()),
        new MuestraDescripcion(command.descripcion()));
  }

  public MuestraModel fromUpdateCommandToModel(final UpdateMuestraCommand command) {
    return new MuestraModel(
        new MuestraId(command.id()),
        new MuestraCodigo(command.codigo()),
        new LoteId(command.loteId()),
        new MuestraDescripcion(command.descripcion()),
        MuestraEstado.fromString(command.estado()));
  }

  public MuestraId fromGetMuestraByIdQueryToMuestraId(final GetMuestraByIdQuery query) {
    return new MuestraId(query.id());
  }

  public MuestraId fromDeleteCommandToMuestraId(final DeleteMuestraCommand command) {
    return new MuestraId(command.id());
  }
}
