package com.fiap.challenge.parking.repository;

import com.fiap.challenge.parking.dominio.condutor.entidade.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondutorRepository extends JpaRepository<Condutor, Long> {

}