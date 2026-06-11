package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.MuestraNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.MuestraResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.MuestraController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.MuestraResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateMuestraRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateMuestraHandler implements OperationHandler {

  private final MuestraController muestraController;
  private final ConsoleIO console;
  private final MuestraResponsePrinter printer;

  @Override
  public void handle() {
    final String id          = console.readRequired("Muestra ID                              : ");
    final String codigo      = console.readRequired("New codigo                              : ");
    final String loteId      = console.readRequired("New lote ID                             : ");
    final String descripcion = console.readRequired("New descripcion                         : ");
    final String estado      = console.readRequired("Estado (DISPONIBLE/EN_ANALISIS/AGOTADA) : ");

    try {
      final MuestraResponse updated = muestraController.updateMuestra(
          new UpdateMuestraRequest(id, codigo, loteId, descripcion, estado));
      console.println("\n  Muestra updated successfully.");
      printer.print(updated);
    } catch (final MuestraNotFoundException exception) {
      console.println("  Not found: " + exception.getMessage());
    }
  }
}
