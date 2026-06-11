package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.MuestraResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MuestraResponsePrinter {

  private static final String SEPARATOR = "-".repeat(52);
  private static final String ROW_FORMAT = "  %-12s : %s%n";

  private final ConsoleIO console;

  public void print(final MuestraResponse response) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID",          response.id());
    console.printf(ROW_FORMAT, "Codigo",      response.codigo());
    console.printf(ROW_FORMAT, "Lote ID",     response.loteId());
    console.printf(ROW_FORMAT, "Descripcion", response.descripcion());
    console.printf(ROW_FORMAT, "Estado",      response.estado());
    console.println(SEPARATOR);
  }

  public void printList(final List<MuestraResponse> muestras) {
    if (muestras.isEmpty()) {
      console.println("  No muestras found.");
      return;
    }
    console.printf("%n  Total: %d muestra(s)%n", muestras.size());
    muestras.forEach(this::print);
  }
}
