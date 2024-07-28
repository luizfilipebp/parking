package com.fiap.challenge.parking.controller.dto.Periodo;

public record PeriodoGetResponseBody(
        long id,
        String cpf,
        String nome,
        String endereco,
        String telefone
) {
}
