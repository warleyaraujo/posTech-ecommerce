package com.postech.gestaodeenvio.services;

import com.postech.gestaodeenvio.entities.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

//ESSE SERVICE NAO IRA PERSISTIR O PEDIDO, apenas comunicar com a API de envio.
@Service
public interface PedidoService {

    public ResponseEntity<?> importarPedido(Pedido pedido) throws IOException, InterruptedException;
}
