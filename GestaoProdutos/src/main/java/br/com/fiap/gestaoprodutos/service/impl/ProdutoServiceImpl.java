package br.com.fiap.gestaoprodutos.service.impl;

import br.com.fiap.gestaoprodutos.entities.Produto;
import br.com.fiap.gestaoprodutos.reporitories.ProdutoRepository;
import br.com.fiap.gestaoprodutos.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public Page<Produto> listaProdutos(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    @Override
    public Produto getProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado para o ID: " + id));
    }

    @Override
    public Produto salvarProduto(Produto produto) {
        if (produto.getNome() == null || produto.getDescricao() == null || produto.getPreco() == null)
            throw new IllegalArgumentException("Preencha corretamente os dados de Produto");

        if (produto.getQuantidadeEstoque() == null)
            produto.setQuantidadeEstoque(0);

        return produtoRepository.save(produto);
    }

    @Override
    public Produto updateProduto(Long id, Produto produtoNovo) {
        Produto produto = this.getProdutoPorId(id);

        if (produtoNovo.getNome() == null || produtoNovo.getDescricao() == null
                || produtoNovo.getPreco() == null || produtoNovo.getQuantidadeEstoque() == null
                || produtoNovo.getPreco().compareTo(BigDecimal.ZERO) <= 0
                || produtoNovo.getQuantidadeEstoque() < 0)
            throw new IllegalArgumentException("Preencha corretamente os dados que devem ser atualizados");

        produto.setNome(produtoNovo.getNome());
        produto.setDescricao(produtoNovo.getDescricao());
        produto.setPreco(produtoNovo.getPreco());
        produto.setQuantidadeEstoque(produtoNovo.getQuantidadeEstoque());

        return produtoRepository.save(produto);
    }

    @Override
    public void removeEstoque(Long id, Integer quantidade) {
        Produto produto = this.getProdutoPorId(id);

        if (produto.getQuantidadeEstoque() < quantidade) {
            throw new IllegalArgumentException("quantidade desejada é maior que o estoque");
        }
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);

        produtoRepository.save(produto);
    }

    @Override
    public boolean deleteProduto(Long id) {
        Produto produto = this.getProdutoPorId(id);

        produtoRepository.delete(produto);
        return true;
    }

}
