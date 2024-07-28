package com.fiap.challenge.parking.controller.dto.Periodo;

import com.fiap.challenge.parking.dominio.periodo.emun.TipoPeriodo;

import java.time.LocalDateTime;

public record PeriodoPostRequestBody(
        LocalDateTime inicio,
        String cpf,
        String placa,
        LocalDateTime periodoContratado,
        TipoPeriodo tipoPeriodo
) {

}
