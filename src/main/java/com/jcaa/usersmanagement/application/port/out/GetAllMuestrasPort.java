package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.MuestraModel;
import java.util.List;

public interface GetAllMuestrasPort {
  List<MuestraModel> getAll();
}
