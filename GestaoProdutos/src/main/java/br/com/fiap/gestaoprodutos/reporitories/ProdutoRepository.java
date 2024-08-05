package br.com.fiap.gestaoprodutos.reporitories;

import br.com.fiap.gestaoprodutos.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
