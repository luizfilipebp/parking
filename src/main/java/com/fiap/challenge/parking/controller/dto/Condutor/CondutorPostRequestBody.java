package com.fiap.challenge.parking.controller.dto.Condutor;

import jakarta.validation.constraints.NotBlank;

public record CondutorPostRequestBody(
        @NotBlank(message = "CPF não pode ser vazio")
        String cpf,
        String nome,
        String endereco,
        String telefone
) {

}
