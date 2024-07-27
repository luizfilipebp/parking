package com.fiap.challenge.parking.services;

import com.fiap.challenge.parking.controller.dto.Condutor.CondutorPostRequestBody;
import com.fiap.challenge.parking.controller.dto.Condutor.CondutorPostResponseBody;
import com.fiap.challenge.parking.controller.dto.Condutor.CondutorPutRequestBody;
import com.fiap.challenge.parking.controller.dto.Condutor.CondutorPutResponseBody;
import com.fiap.challenge.parking.dominio.condutor.entidade.Condutor;
import com.fiap.challenge.parking.repository.CondutorRepository;
import com.fiap.challenge.parking.services.exceptions.BadRequestException;
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
public class CondutorService {

    private final CondutorRepository condutorRepositorio;

    @Transactional(readOnly = true)
    public Page<Condutor> findAllPaged(Pageable pageable) {
        return condutorRepositorio.findAll(pageable);
    }

    @Transactional
    public CondutorPostResponseBody insert(CondutorPostRequestBody dto) {
        Condutor entity = new Condutor();
        requestBodyToEntity(dto, entity);
        entity = condutorRepositorio.save(entity);
        return entityToResponseBody(entity);
    }

    @Transactional(readOnly = true)
    public Condutor findById(Long id) {
        Optional<Condutor> obj = condutorRepositorio.findById(id);
        Condutor entity = obj.orElseThrow(() -> new BadRequestException("Objeto não encontrado, id: " + id));
        return entity;
    }

    @Transactional
    public CondutorPutResponseBody update(Long id, CondutorPutRequestBody request) {
        try {
            Condutor entity = condutorRepositorio.getReferenceById(id);
            putRequestToEntity(request, entity);
            entity = condutorRepositorio.save(entity);
            return new CondutorPutResponseBody(entity.getId());
        } catch (EntityNotFoundException e) {
            throw new BadRequestException("Objeto não encontrado, id: " + id);
        }
    }

    public void delete(Long id) {
        try {
            condutorRepositorio.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new BadRequestException("Objeto não encontrado, id: " + id);
        } catch (DataIntegrityViolationException e) {
//            throw new DatabaseException("Violação de integridade!");
        }
    }

    private void requestBodyToEntity(CondutorPostRequestBody dto, Condutor entity) {
        entity.setNome(dto.nome());
        entity.setCpf(dto.cpf());
        entity.setEndereco(dto.endereco());
        entity.setTelefone(dto.telefone());
    }

    private CondutorPostRequestBody entityToRequestBody(Condutor entity) {
        return new CondutorPostRequestBody(
                entity.getCpf(),
                entity.getNome(),
                entity.getEndereco(),
                entity.getTelefone()
        );
    }

    private void putRequestToEntity(CondutorPutRequestBody req, Condutor entity) {
        entity.setNome(req.nome());
        entity.setCpf(req.cpf());
        entity.setEndereco(req.endereco());
        entity.setTelefone(req.telefone());
    }


    private CondutorPostResponseBody entityToResponseBody(Condutor entity) {
        return new CondutorPostResponseBody(
                entity.getId(),
                entity.getCpf()
        );
    }
}
