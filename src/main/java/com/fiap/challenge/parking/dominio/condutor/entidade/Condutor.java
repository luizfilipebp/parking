package com.fiap.challenge.parking.dominio.condutor.entidade;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "condutor")
public class Condutor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "con_id")
    private Long id;

    @Column(name = "con_cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "con_nome", nullable = false)
    private String nome;

    @Column(name = "con_endereco")
    private String endereco;

    @Column(name = "con_telefone")
    private String telefone;


    public Condutor(String cpf, String nome, String endereco, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }
}
