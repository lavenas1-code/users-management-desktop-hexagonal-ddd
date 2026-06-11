package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.MuestraModel;
import java.util.List;

public interface GetAllMuestrasUseCase {
  List<MuestraModel> execute();
}
