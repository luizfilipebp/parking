package com.fiap.challenge.parking.controller;

import com.fiap.challenge.parking.controller.dto.CondutorDTO;
import com.fiap.challenge.parking.services.CondutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/condutor")
public class CondutorController {

    @Autowired
    private CondutorService consdutorService;

//    @GetMapping
//    public ResponseEntity<Page<CondutorDTO>> findAll(
//            @RequestParam(value = "nome", defaultValue = "") String nome,
//            Pageable pageable) {
//        Page<CondutorDTO> page = consdutorService.findAllPaged(nome.trim(), pageable);
//        return ResponseEntity.ok().body();
//    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CondutorDTO> findById(@PathVariable Long id) {
        CondutorDTO dto = consdutorService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<CondutorDTO> insert(@Valid @RequestBody CondutorDTO pessoaDto) {
        pessoaDto = consdutorService.insert(pessoaDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoaDto.getId()).toUri();
        return ResponseEntity.created(uri).body(pessoaDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CondutorDTO> update(@PathVariable Long id, @Valid @RequestBody CondutorDTO dto) {

        dto = consdutorService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        consdutorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}