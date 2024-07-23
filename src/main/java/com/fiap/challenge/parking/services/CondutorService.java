package com.fiap.challenge.parking.services;

import com.fiap.challenge.parking.controller.dto.CondutorDTO;
import com.fiap.challenge.parking.dominio.condutor.entidade.Condutor;
import com.fiap.challenge.parking.repository.CondutorRepository;
import com.fiap.challenge.parking.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.Optional;

@Service
public class CondutorService {
    @Autowired
    private CondutorRepository condutorRepositorio;

//    @Transactional(readOnly = true)
//    public Page<CondutorDTO> findAllPaged(String nome, String sexo, String parentesco, Pageable pageable) {
//        Page<Condutor> page = condutorRepositorio.findById();
//        return page.map(x -> new CondutorDTO(x));
//    }

    @Transactional
    public CondutorDTO insert(CondutorDTO dto) {
        Condutor entity = new Condutor();
        copyDtoToEntity(dto, entity);
        entity = condutorRepositorio.save(entity);
        return new CondutorDTO(entity);
    }

    @Transactional(readOnly = true)
    public CondutorDTO findById(Long id) {
        Optional<Condutor> obj = condutorRepositorio.findById(id);
        Condutor entity = obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado, id: " + id));
        return new CondutorDTO(entity);
    }

    @Transactional
    public CondutorDTO update(Long id, CondutorDTO dto) {
        try {
            Condutor entity = condutorRepositorio.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = condutorRepositorio.save(entity);
            return new CondutorDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Objeto não encontrado, id: " + id);
        }
    }

    public void delete(Long id) {
        try {
            condutorRepositorio.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Objeto não encontrado, id: " + id);
        } catch (DataIntegrityViolationException e) {
//            throw new DatabaseException("Violação de integridade!");
        }
    }

    private void copyDtoToEntity(CondutorDTO CondutorDTO, Condutor pessoaEntity) {
        pessoaEntity.setNome(CondutorDTO.getNome());
    }
}
