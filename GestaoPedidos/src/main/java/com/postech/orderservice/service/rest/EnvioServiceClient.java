package com.postech.orderservice.service.rest;

import com.postech.orderservice.entities.Pedido;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "envio-service", url = "http://localhost:8091")
public interface EnvioServiceClient {

    @PostMapping("/pedidos/importar")
    ResponseEntity<Void> enviarPedido(@RequestBody Pedido pedido);

}
