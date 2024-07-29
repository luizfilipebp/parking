package com.fiap.challenge.parking.controller.dto.Veiculo;

import jakarta.validation.constraints.NotBlank;

public record VeiculoPostRequestBody(
        @NotBlank(message = "O campo placa não pode ser vazio")
        String placa
) {

}
