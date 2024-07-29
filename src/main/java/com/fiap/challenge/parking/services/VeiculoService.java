package com.fiap.challenge.parking.services;

import com.fiap.challenge.parking.controller.dto.Veiculo.VeiculoPostRequestBody;
import com.fiap.challenge.parking.controller.dto.Veiculo.VeiculoPostResponseBody;
import com.fiap.challenge.parking.controller.dto.Veiculo.VeiculoPutRequestBody;
import com.fiap.challenge.parking.controller.dto.Veiculo.VeiculoPutResponseBody;
import com.fiap.challenge.parking.dominio.veiculo.entidade.Veiculo;
import com.fiap.challenge.parking.repository.VeiculoRepository;
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
public class VeiculoService {

    private final VeiculoRepository veiculoRepositorio;

    @Transactional(readOnly = true)
    public Page<Veiculo> findAllPaged(Pageable pageable) {
        return veiculoRepositorio.findAll(pageable);
    }

    @Transactional
    public VeiculoPostResponseBody insert(VeiculoPostRequestBody dto) {
        Veiculo entity = new Veiculo();
        requestBodyToEntity(dto, entity);
        entity = veiculoRepositorio.save(entity);
        return entityToResponseBody(entity);
    }

    @Transactional(readOnly = true)
    public Veiculo findById(Long id) {
        Optional<Veiculo> obj = veiculoRepositorio.findById(id);
        Veiculo entity = obj.orElseThrow(() -> new BadRequestException("Objeto não encontrado, id: " + id));
        return entity;
    }

    @Transactional
    public VeiculoPutResponseBody update(Long id, VeiculoPutRequestBody request) {
        try {
            Veiculo entity = veiculoRepositorio.getReferenceById(id);
            putRequestToEntity(request, entity);
            entity = veiculoRepositorio.save(entity);
            return new VeiculoPutResponseBody(entity.getId());
        } catch (EntityNotFoundException e) {
            throw new BadRequestException("Objeto não encontrado, id: " + id);
        }
    }

    public void delete(Long id) {
        try {
            veiculoRepositorio.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new BadRequestException("Objeto não encontrado, id: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade!");
        }
    }

    private void requestBodyToEntity(VeiculoPostRequestBody dto, Veiculo entity) {
        entity.setPlaca(dto.placa());
    }

    private void putRequestToEntity(VeiculoPutRequestBody req, Veiculo entity) {
        entity.setPlaca(req.placa());
    }

    private VeiculoPostResponseBody entityToResponseBody(Veiculo entity) {
        return new VeiculoPostResponseBody(
                entity.getId(),
                entity.getPlaca()
        );
    }
}
