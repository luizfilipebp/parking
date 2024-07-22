package com.fiap.challenge.parking.dominio.condutor.entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "condutor")
public class Condutor {
    @Id
    private Long id;
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;


    public Condutor(Long id, String cpf, String nome, String endereco, String telefone) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Condutor() {
    }
}
