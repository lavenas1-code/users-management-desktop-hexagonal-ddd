package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.MuestraNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.MuestraController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteMuestraHandler implements OperationHandler {

  private final MuestraController muestraController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final String id = console.readRequired("Muestra ID to delete: ");
    try {
      muestraController.deleteMuestra(id);
      console.println("  Muestra deleted successfully.");
    } catch (final MuestraNotFoundException exception) {
      console.println("  Not found: " + exception.getMessage());
    }
  }
}
