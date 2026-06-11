package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record MuestraResponse(
    String id,
    String codigo,
    String loteId,
    String descripcion,
    String estado) {}
