package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.MuestraAlreadyExistsException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.MuestraResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.MuestraController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateMuestraRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.MuestraResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateMuestraHandler implements OperationHandler {

  private final MuestraController muestraController;
  private final ConsoleIO console;
  private final MuestraResponsePrinter printer;

  @Override
  public void handle() {
    final String id          = console.readRequired("ID                              : ");
    final String codigo      = console.readRequired("Codigo                          : ");
    final String loteId      = console.readRequired("Lote ID                         : ");
    final String descripcion = console.readRequired("Descripcion                     : ");

    try {
      final MuestraResponse created =
          muestraController.createMuestra(new CreateMuestraRequest(id, codigo, loteId, descripcion));
      console.println("\n  Muestra created successfully.");
      printer.print(created);
    } catch (final MuestraAlreadyExistsException exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }
}
