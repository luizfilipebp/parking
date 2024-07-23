package com.fiap.challenge.parking.controller.dto;

import com.fiap.challenge.parking.dominio.condutor.entidade.Condutor;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class CondutorDTO {
    private static final long serialVersionUID = 1L;
    private Long id;

    @NotBlank(message = "Nome não pode estar em branco e não pode ser nulo.")
    private String nome;

    public CondutorDTO(Condutor entity){
        this.id = entity.getId();
        this.nome = entity.getNome();
    }
}
