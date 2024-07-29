package com.fiap.challenge.parking.repository;

import com.fiap.challenge.parking.dominio.periodo.entidade.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PeriodoRepository extends JpaRepository<Periodo, Long> {

    @Query("""
            SELECT p from Periodo p
            WHERE p.periodoContratado  between CURRENT_TIMESTAMP  AND :data
            AND p.notificado != true
            """)
    List<Periodo> findAllNotificable(@Param("data") LocalDateTime dataAtualMaisQuinze);
}