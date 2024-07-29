package com.fiap.challenge.parking.dominio.pagamento.entidade;

import com.fiap.challenge.parking.dominio.condutor.entidade.Condutor;
import com.fiap.challenge.parking.dominio.veiculo.entidade.Veiculo;
import lombok.Data;

@Data
public class Pagamento {

    private String id;

    private String placa;

    private Veiculo veiculo;

    private Condutor condutor;

    private String formaDePagamento;

}
