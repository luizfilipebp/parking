package com.fiap.challenge.parking.dominio.veiculo.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Veiculo {
    @Id
    private Long id;

    @Column(nullable = false)
    private String placa;
}
