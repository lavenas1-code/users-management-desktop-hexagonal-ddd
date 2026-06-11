package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.MuestraNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.MuestraResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.MuestraController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.MuestraResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindMuestraByIdHandler implements OperationHandler {

  private final MuestraController muestraController;
  private final ConsoleIO console;
  private final MuestraResponsePrinter printer;

  @Override
  public void handle() {
    final String id = console.readRequired("Muestra ID: ");
    try {
      final MuestraResponse muestra = muestraController.findMuestraById(id);
      printer.print(muestra);
    } catch (final MuestraNotFoundException exception) {
      console.println("  Not found: " + exception.getMessage());
    }
  }
}
