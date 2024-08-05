package br.com.fiap.gestaoprodutos.utils;

import br.com.fiap.gestaoprodutos.entities.Produto;

import java.math.BigDecimal;

public abstract class ProdutoHelper {

    public static Produto gerarProduto() {
        return Produto.builder()
                .id(1L)
                .nome("Produto Teste")
                .descricao("Produto de teste automatizado")
                .quantidadeEstoque(10)
                .preco(BigDecimal.valueOf(50.00))
                .build();
    }
}
