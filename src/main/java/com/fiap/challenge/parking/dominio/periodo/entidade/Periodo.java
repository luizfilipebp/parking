package com.fiap.challenge.parking.dominio.periodo.entidade;

import com.fiap.challenge.parking.dominio.periodo.emun.TipoPeriodo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Data
@Entity
public class Periodo {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PER_ID")
    private Long id;

    @Setter(AccessLevel.NONE)
    @Column(name = "PER_INICIO", nullable = false)
    private LocalDateTime inicio;

    @Column(name = "PER_FIM")
    @Setter(AccessLevel.NONE)
    private LocalDateTime fim;

    @Column(name = "PER_CON_CPF", nullable = false)
    private String cpf;

    @Column(name = "PER_VEI_PLACA", nullable = false)
    private String placa;

    @Column(name = "PER_VALOR")
    private BigDecimal valor;

    @Column(name = "PER_CONTRATADO")
    private LocalDateTime periodoContratado; // Horas contratadas no periodo Fixo

    @Column(name = "PER_TIPO", nullable = false)
    private TipoPeriodo tipoPeriodo;

    @Column(name = "PER_NOTIFICADO")
    private boolean notificado;

    public Periodo() {
        this.inicio = LocalDateTime.now();
        this.notificado = false;
    }

//    public abstract Duration encerrarPeriodo();
}
