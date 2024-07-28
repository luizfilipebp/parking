package com.fiap.challenge.parking.services;

import com.fiap.challenge.parking.controller.dto.Periodo.PeriodoPostRequestBody;
import com.fiap.challenge.parking.controller.dto.Periodo.PeriodoPostResponseBody;
import com.fiap.challenge.parking.controller.dto.Periodo.PeriodoPutRequestBody;
import com.fiap.challenge.parking.controller.dto.Periodo.PeriodoPutResponseBody;
import com.fiap.challenge.parking.dominio.periodo.entidade.Periodo;
import com.fiap.challenge.parking.repository.PeriodoRepository;
import com.fiap.challenge.parking.services.exceptions.BadRequestException;
import com.fiap.challenge.parking.services.exceptions.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeriodoService {

    private final PeriodoRepository periodoRepositorio;

    @Transactional(readOnly = true)
    public Page<Periodo> findAllPaged(Pageable pageable) {
        return periodoRepositorio.findAll(pageable);
    }

    @Transactional
    public PeriodoPostResponseBody insert(PeriodoPostRequestBody dto) {
        Periodo entity = new Periodo();
        requestBodyToEntity(dto, entity);
        entity = periodoRepositorio.save(entity);
        return entityToResponseBody(entity);
    }

    @Transactional(readOnly = true)
    public Periodo findById(Long id) {
        Optional<Periodo> obj = periodoRepositorio.findById(id);
        Periodo entity = obj.orElseThrow(() -> new BadRequestException("Objeto não encontrado, id: " + id));
        return entity;
    }

    @Transactional
    public PeriodoPutResponseBody update(Long id, PeriodoPutRequestBody request) {
        try {
            Periodo entity = periodoRepositorio.getReferenceById(id);
            putRequestToEntity(request, entity);
            entity = periodoRepositorio.save(entity);
            return new PeriodoPutResponseBody(entity.getId());
        } catch (EntityNotFoundException e) {
            throw new BadRequestException("Objeto não encontrado, id: " + id);
        }
    }

    public void delete(Long id) {
        try {
            periodoRepositorio.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new BadRequestException("Objeto não encontrado, id: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade!");
        }
    }

    public void mudarParaNotificado(Periodo periodo) {
            periodo.setNotificado(true);
            periodoRepositorio.save(periodo);
    }

    private void requestBodyToEntity(PeriodoPostRequestBody dto, Periodo entity) {
        entity.setCpf(dto.cpf());
        entity.setTipoPeriodo(dto.tipoPeriodo());
        entity.setPeriodoContratado(dto.periodoContratado());
        entity.setPlaca(dto.placa());
    }

    private void putRequestToEntity(PeriodoPutRequestBody req, Periodo entity) {
        entity.setPlaca(req.cpf());
    }

    private PeriodoPostResponseBody entityToResponseBody(Periodo entity) {
        return new PeriodoPostResponseBody(
                entity.getId(),
                entity.getPlaca()
        );
    }
}
