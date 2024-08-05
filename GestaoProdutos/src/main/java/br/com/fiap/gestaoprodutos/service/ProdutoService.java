package br.com.fiap.gestaoprodutos.service;

import br.com.fiap.gestaoprodutos.entities.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoService {

    Page<Produto> listaProdutos(Pageable pageable);
    Produto getProdutoPorId(Long id);
    Produto salvarProduto(Produto produto);
    Produto updateProduto(Long id, Produto produtoNovo);
    void removeEstoque(Long id, Integer quantidade);
    boolean deleteProduto(Long id);

}
