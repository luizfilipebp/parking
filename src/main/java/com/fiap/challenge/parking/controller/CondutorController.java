package com.fiap.challenge.parking.controller;

import com.fiap.challenge.parking.controller.dto.Condutor.CondutorPostRequestBody;
import com.fiap.challenge.parking.controller.dto.Condutor.CondutorPostResponseBody;
import com.fiap.challenge.parking.controller.dto.Condutor.CondutorPutRequestBody;
import com.fiap.challenge.parking.controller.dto.Condutor.CondutorPutResponseBody;
import com.fiap.challenge.parking.dominio.condutor.entidade.Condutor;
import com.fiap.challenge.parking.services.CondutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/condutor")
public class CondutorController {

    private final CondutorService condutorService;

    @GetMapping
    public ResponseEntity<Page<Condutor>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(condutorService.findAllPaged(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Condutor> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(condutorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CondutorPostResponseBody> insert(@Valid @RequestBody CondutorPostRequestBody dto) {
        CondutorPostResponseBody response = condutorService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CondutorPutResponseBody> update(@PathVariable Long id, @Valid @RequestBody CondutorPutRequestBody req) {
        CondutorPutResponseBody updated = condutorService.update(id, req);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        condutorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}