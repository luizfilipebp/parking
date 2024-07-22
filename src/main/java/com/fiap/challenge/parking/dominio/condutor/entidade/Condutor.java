package com.fiap.challenge.parking.dominio.condutor.entidade;

import lombok.Data;

@Data
public class Condutor {
    private Long id;
    private String cpf;
    private String nome;

    public Condutor(Long id, String cpf, String nome) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
    }

    public Condutor() {
    }
}
