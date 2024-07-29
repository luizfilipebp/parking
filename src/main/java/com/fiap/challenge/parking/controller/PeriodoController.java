package com.fiap.challenge.parking.controller;

import com.fiap.challenge.parking.controller.dto.Periodo.PeriodoPostRequestBody;
import com.fiap.challenge.parking.controller.dto.Periodo.PeriodoPostResponseBody;
import com.fiap.challenge.parking.controller.dto.Periodo.PeriodoPutRequestBody;
import com.fiap.challenge.parking.controller.dto.Periodo.PeriodoPutResponseBody;
import com.fiap.challenge.parking.dominio.periodo.entidade.Periodo;
import com.fiap.challenge.parking.services.PeriodoService;
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
@RequestMapping("/periodo")
public class PeriodoController {

    private final PeriodoService periodoService;

    @GetMapping
    public ResponseEntity<Page<Periodo>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(periodoService.findAllPaged(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Periodo> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(periodoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PeriodoPostResponseBody> insert(@Valid @RequestBody PeriodoPostRequestBody dto) {
        PeriodoPostResponseBody response = periodoService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PeriodoPutResponseBody> update(@PathVariable Long id, @Valid @RequestBody PeriodoPutRequestBody req) {
        PeriodoPutResponseBody updated = periodoService.update(id, req);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        periodoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}