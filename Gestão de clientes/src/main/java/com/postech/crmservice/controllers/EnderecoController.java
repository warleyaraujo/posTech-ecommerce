package com.postech.crmservice.controllers;

import com.postech.crmservice.entities.DTOs.EnderecoDto;
import com.postech.crmservice.services.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/enderecos")
@Tag(name = "Cadastro de Endereços", description = "Endpoint para manutenção no cadastro de endereços")
public class EnderecoController {
    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    @Operation(summary = "Efetua a inclusão de um novo endereço", method = "POST")
    public ResponseEntity<EnderecoDto> save(@RequestBody EnderecoDto enderecoDto) {
        enderecoService.save(enderecoDto);
        return ResponseEntity.ok(enderecoDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Efetua a alteração de um endereço existente", method = "PUT")
    public ResponseEntity<EnderecoDto> update(@PathVariable Long id, @RequestBody EnderecoDto enderecoDto) {
        enderecoService.update(id, enderecoDto);
        return ResponseEntity.ok(enderecoDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Efetua a exclusão de um endereço existente", method = "DELETE")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtem os dados de um endereço existente", method = "GET")
    public ResponseEntity<EnderecoDto> getById(@PathVariable Long id) {
        EnderecoDto enderecoDto = enderecoService.getById(id);
        return ResponseEntity.ok(enderecoDto);
    }

    @GetMapping
    @Operation(summary = "Obtem uma lista de endereços", method = "GET")
    public ResponseEntity<List<EnderecoDto>> getAll() {
        List<EnderecoDto> enderecoDtos = enderecoService.getAll();
        return ResponseEntity.ok(enderecoDtos);
    }
}
