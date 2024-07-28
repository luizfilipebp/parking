package com.fiap.challenge.parking.dominio.veiculo.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
public class Veiculo {
    @Id
    @Column(name = "VEI_ID")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "VEI_PLACA", nullable = false)
    private String placa;
}
