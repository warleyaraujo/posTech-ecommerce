package com.postech.crmservice.controllers;

import com.postech.crmservice.entities.DTOs.ClienteDto;
import com.postech.crmservice.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
@Tag(name = "Cadastro de Clientes", description = "Endpoint para manutenção no cadastro de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @PostMapping
    @Operation(summary = "Efetua a inclusão de um novo cliente", method = "POST")
    public ResponseEntity<ClienteDto> save(@RequestBody ClienteDto clienteDto) {
        clienteService.save(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Efetua a alteração de um cliente existente", method = "PUT")
    public ResponseEntity<ClienteDto> update(@PathVariable Long id,@RequestBody ClienteDto clienteDto) {
        clienteService.update(id, clienteDto);
        return ResponseEntity.ok(clienteDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Efetua a exclusão de um cliente existente", method = "DELETE")
    public ResponseEntity<?> delete(@PathVariable String id) {
        clienteService.delete(Long.valueOf(id));
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtem dados de um cliente existente", method = "GET")
    public ResponseEntity<ClienteDto> getById(@PathVariable Long id) {
        ClienteDto clienteDto = clienteService.getById(id);
        return ResponseEntity.ok(clienteDto);
    }

    @GetMapping
    @Operation(summary = "Obtem uma lista de todos os clientes cadastrados", method = "GET")
    public ResponseEntity<List<ClienteDto>> getAll() {
        List<ClienteDto> clienteDtos = clienteService.getAll();
        return ResponseEntity.ok(clienteDtos);
    }
}
