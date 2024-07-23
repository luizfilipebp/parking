package com.fiap.challenge.parking.repository;

import com.fiap.challenge.parking.dominio.condutor.entidade.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondutorRepository extends JpaRepository<Condutor, Long> {

//    @Query("SELECT DISTINCT obj FROM Pessoa obj "
//            + "WHERE (LOWER(obj.nome) LIKE LOWER(CONCAT('%',:nome,'%'))) "
//            + "AND (LOWER(obj.sexo) LIKE LOWER(CONCAT('%',:sexo,'%'))) "
//            + "AND (LOWER(obj.parentesco) LIKE LOWER(CONCAT('%',:parentesco,'%'))) ")
//    Page<Pessoa> find(String nome, String sexo, String parentesco, Pageable pageable);

}