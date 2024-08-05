package com.postech.orderservice.controllers;

import com.postech.orderservice.entities.Pedido;
import com.postech.orderservice.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedidos")
@Tag(name = "Gestão de Pedidos", description = "Controller para manutenção na Gestão de Pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping(value = "/listarPedidos", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lista todos os Pedidos", method = "GET")
    public ResponseEntity<List<Pedido>> listarPedidos() {
        return new ResponseEntity<>(pedidoService.listarPedidos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um pedido pelo Id", method = "GET")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.buscarPedidoPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    @Operation(summary = "Criar um novo pedido", method = "POST")
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido, @RequestParam Long idCliente) {
        Pedido pedidoResponse = pedidoService.criarPedido(pedido, idCliente);
        return new ResponseEntity<>(pedidoResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um pedido cadastrado", method = "PUT")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @RequestBody Pedido pedidoNovo) {
        Pedido pedidoNovoResponse = pedidoService.atualizarPedido(id, pedidoNovo);
        return new ResponseEntity<>(pedidoNovoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um pedido cadastrado", method = "DELETE")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }

}
