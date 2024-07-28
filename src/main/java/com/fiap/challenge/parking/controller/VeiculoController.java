package com.fiap.challenge.parking.controller;

import com.fiap.challenge.parking.controller.dto.Veiculo.VeiculoPostRequestBody;
import com.fiap.challenge.parking.controller.dto.Veiculo.VeiculoPostResponseBody;
import com.fiap.challenge.parking.controller.dto.Veiculo.VeiculoPutRequestBody;
import com.fiap.challenge.parking.controller.dto.Veiculo.VeiculoPutResponseBody;
import com.fiap.challenge.parking.dominio.veiculo.entidade.Veiculo;
import com.fiap.challenge.parking.services.VeiculoService;
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
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<Page<Veiculo>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(veiculoService.findAllPaged(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Veiculo> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(veiculoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<VeiculoPostResponseBody> insert(@Valid @RequestBody VeiculoPostRequestBody dto) {
        VeiculoPostResponseBody response = veiculoService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VeiculoPutResponseBody> update(@PathVariable Long id, @Valid @RequestBody VeiculoPutRequestBody req) {
        VeiculoPutResponseBody updated = veiculoService.update(id, req);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        veiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}