package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.enums.MuestraEstado;
import com.jcaa.usersmanagement.domain.model.MuestraModel;
import com.jcaa.usersmanagement.domain.valueobject.LoteId;
import com.jcaa.usersmanagement.domain.valueobject.MuestraCodigo;
import com.jcaa.usersmanagement.domain.valueobject.MuestraDescripcion;
import com.jcaa.usersmanagement.domain.valueobject.MuestraId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.MuestraPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.MuestraEntity;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class MuestraPersistenceMapper {

  public MuestraPersistenceDto fromModelToDto(final MuestraModel muestra) {
    return new MuestraPersistenceDto(
        muestra.getId().value(),
        muestra.getCodigo().value(),
        muestra.getLoteId().value(),
        muestra.getDescripcion().value(),
        muestra.getEstado().name(),
        null,
        null);
  }

  public MuestraEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
    return new MuestraEntity(
        resultSet.getString("id"),
        resultSet.getString("codigo"),
        resultSet.getString("lote_id"),
        resultSet.getString("descripcion"),
        resultSet.getString("estado"),
        resultSet.getString("created_at"),
        resultSet.getString("updated_at"));
  }

  public MuestraModel fromEntityToModel(final MuestraEntity entity) {
    return new MuestraModel(
        new MuestraId(entity.id()),
        new MuestraCodigo(entity.codigo()),
        new LoteId(entity.loteId()),
        new MuestraDescripcion(entity.descripcion()),
        MuestraEstado.fromString(entity.estado()));
  }

  public MuestraModel fromResultSetToModel(final ResultSet resultSet) throws SQLException {
    return fromEntityToModel(fromResultSetToEntity(resultSet));
  }

  public List<MuestraModel> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
    final List<MuestraModel> muestras = new ArrayList<>();
    while (resultSet.next()) {
      muestras.add(fromResultSetToModel(resultSet));
    }
    return muestras;
  }
}
