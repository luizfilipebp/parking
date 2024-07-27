package com.fiap.challenge.parking.repository;

import com.fiap.challenge.parking.dominio.condutor.entidade.Condutor;
import com.fiap.challenge.parking.dominio.veiculo.entidade.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}