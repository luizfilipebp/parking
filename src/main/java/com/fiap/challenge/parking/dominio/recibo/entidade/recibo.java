package com.fiap.challenge.parking.dominio.recibo.entidade;

import com.fiap.challenge.parking.dominio.condutor.entidade.Condutor;
import com.fiap.challenge.parking.dominio.pagamento.entidade.Pagamento;
import com.fiap.challenge.parking.dominio.veiculo.entidade.Veiculo;
import lombok.Data;

@Data
public class recibo {

    private String id;

    private Veiculo veiculo;

    private Condutor condutor;

    private Pagamento pagamento;

    private String emitirRecibo;

}
