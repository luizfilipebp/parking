package com.fiap.challenge.parking.controller.dto.Periodo;

import jakarta.validation.constraints.NotBlank;

public record PeriodoPutRequestBody(
        @NotBlank(message = "CPF não pode ser vazio")
        String cpf,
        String nome,
        String endereco,
        String telefone
) {
}
