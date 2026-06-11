package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateMuestraCommand(
    @NotBlank(message = "id must not be blank") String id,
    @NotBlank(message = "codigo must not be blank")
        @Pattern(regexp = "^[A-Za-z0-9-]{3,20}$", message = "codigo format is invalid")
        String codigo,
    @NotBlank(message = "loteId must not be blank") String loteId,
    @NotBlank(message = "descripcion must not be blank")
        @Size(min = 3, message = "descripcion must have at least 3 characters")
        String descripcion)
{

}
