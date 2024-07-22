package com.fiap.challenge.parking.dominio.periodo.entidade;

import com.fiap.challenge.parking.dominio.periodo.emun.TipoPeriodo;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Data
public class Periodo {

    private Long id;

    private TipoPeriodo tipoPeriodo; // Seleciona o tipo de periodo FIXO ou VARIAVEL
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private int periodoFixoContratado; // Horas contratadas no periodo Fixo
    private Double valor;

    public Periodo(){}

    public Periodo(Long id, int periodoFixoContratado, TipoPeriodo tipoPeriodo){
        this.id = id;
        this.tipoPeriodo = tipoPeriodo;
        this.inicio = LocalDateTime.now();
        this.periodoFixoContratado = periodoFixoContratado;
        this.fim = inicio.plus(periodoFixoContratado, ChronoUnit.HOURS);
    }


}
