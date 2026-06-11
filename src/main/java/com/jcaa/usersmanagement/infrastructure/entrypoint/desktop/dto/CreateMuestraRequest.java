package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record CreateMuestraRequest(
    String id,
    String codigo,
    String loteId,
    String descripcion) {}
