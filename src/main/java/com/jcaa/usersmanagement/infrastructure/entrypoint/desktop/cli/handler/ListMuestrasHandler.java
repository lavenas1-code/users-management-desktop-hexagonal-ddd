package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.MuestraResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.MuestraController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.MuestraResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListMuestrasHandler implements OperationHandler {

  private final MuestraController muestraController;
  private final MuestraResponsePrinter printer;

  @Override
  public void handle() {
    final List<MuestraResponse> muestras = muestraController.listAllMuestras();
    printer.printList(muestras);
  }
}
