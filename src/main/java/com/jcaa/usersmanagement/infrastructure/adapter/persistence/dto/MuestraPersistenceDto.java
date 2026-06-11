package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record MuestraPersistenceDto(
    String id,
    String codigo,
    String loteId,
    String descripcion,
    String estado,
    String createdAt,
    String updatedAt) {}
