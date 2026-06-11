package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.MuestraEstado;
import com.jcaa.usersmanagement.domain.valueobject.LoteId;
import com.jcaa.usersmanagement.domain.valueobject.MuestraCodigo;
import com.jcaa.usersmanagement.domain.valueobject.MuestraDescripcion;
import com.jcaa.usersmanagement.domain.valueobject.MuestraId;
import lombok.Value;

@Value
public class MuestraModel {

  MuestraId id;
  MuestraCodigo codigo;
  LoteId loteId;
  MuestraDescripcion descripcion;
  MuestraEstado estado;

  public static MuestraModel create(
      final MuestraId id,
      final MuestraCodigo codigo,
      final LoteId loteId,
      final MuestraDescripcion descripcion) {
    return new MuestraModel(id, codigo, loteId, descripcion, MuestraEstado.DISPONIBLE);
  }
}
