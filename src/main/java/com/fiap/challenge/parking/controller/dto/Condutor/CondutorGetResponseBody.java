package com.fiap.challenge.parking.controller.dto.Condutor;

public record CondutorGetResponseBody (
        long id,
        String cpf,
        String nome,
        String endereco,
        String telefone
) {
}
