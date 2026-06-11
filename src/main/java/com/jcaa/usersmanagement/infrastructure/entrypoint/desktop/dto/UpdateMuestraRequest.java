package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record UpdateMuestraRequest(
    String id,
    String codigo,
    String loteId,
    String descripcion,
    String estado) {}
