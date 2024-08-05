package com.postech.gestaodeenvio.controllers;

import com.postech.gestaodeenvio.services.integracao.MelhorEnvioClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/frete")
public class MelhorEnvioController {

    private final MelhorEnvioClient melhorEnvioClient;

    public MelhorEnvioController(MelhorEnvioClient melhorEnvioClient) {
        this.melhorEnvioClient = melhorEnvioClient;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<?> calculaFrete(@PathVariable String cep) throws IOException, InterruptedException {
        return melhorEnvioClient.calcularFrete(cep);
    }

   // @GetMapping("/inserir")
    //public ResponseEntity<?> inserirFrete() throws IOException, InterruptedException {
     //   return melhorEnvioClient.inserirFrete();
    //}

    @GetMapping("/gerarEtiqueta")
    public ResponseEntity<?> gerarEtiqueta() throws IOException, InterruptedException {
        return melhorEnvioClient.gerarEtiqueta();
    }

    @GetMapping("/listarItensCarrinho")
    public ResponseEntity<?> listarItensCarrinho() throws IOException, InterruptedException {
        return melhorEnvioClient.listarItensCarrinho();
    }

    @GetMapping("/exibirInfoItensCarrinho")
    public ResponseEntity<?> exibirInfoItensCarrinho() throws IOException, InterruptedException {
        return melhorEnvioClient.exibirInfoItensCarrinho();
    }

    @GetMapping("/imprimirEtiqueta")
    public ResponseEntity<?> imprimirEtiqueta() throws IOException, InterruptedException {
        return melhorEnvioClient.imprimirEtiqueta();
    }

    @GetMapping("/comprarFrete")
    public ResponseEntity<?> comprarFrete() throws IOException, InterruptedException {
        return melhorEnvioClient.comprarFrete();
    }

    @GetMapping("/rastreioEnvio")
    public ResponseEntity<?> rastreioEnvio() throws IOException, InterruptedException {
        return melhorEnvioClient.rastreioEnvio();
    }
}
