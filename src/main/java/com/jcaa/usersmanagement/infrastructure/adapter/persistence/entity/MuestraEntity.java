package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

public record MuestraEntity(
    String id,
    String codigo,
    String loteId,
    String descripcion,
    String estado,
    String createdAt,
    String updatedAt) {}
