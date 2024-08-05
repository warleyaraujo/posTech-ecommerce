package br.com.fiap.gestaoprodutos.service;

import br.com.fiap.gestaoprodutos.entities.Produto;
import br.com.fiap.gestaoprodutos.reporitories.ProdutoRepository;
import br.com.fiap.gestaoprodutos.service.impl.ProdutoServiceImpl;
import br.com.fiap.gestaoprodutos.utils.ProdutoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProdutoServiceTest {
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    AutoCloseable openMocks;

    @BeforeEach
    public void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        produtoService = new ProdutoServiceImpl(produtoRepository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }

    @Nested
    class RegistrarProduto {
        @Test
        void devePermitirRegistrarProduto() {
            var produto = ProdutoHelper.gerarProduto();

            when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

            var produtoReg = produtoService.salvarProduto(produto);

            assertThat(produtoReg).isInstanceOf(Produto.class).isNotNull();
            assertThat(produtoReg.getId()).isEqualTo(produto.getId());
            assertThat(produtoReg.getNome()).isEqualTo(produto.getNome());
            assertThat(produtoReg.getDescricao()).isNotNull();
        }
    }

    @Nested
    class BuscarProduto {
        @Test
        void devePermitirBuscarProdutoPorId() {
            var id = 1L;
            var produto = ProdutoHelper.gerarProduto();

            when(produtoRepository.findById(any(Long.class)))
                    .thenReturn(Optional.of(produto));

            var produtoObtido = produtoService.getProdutoPorId(id);

            verify(produtoRepository, times(1)).findById(id);
            assertThat(produtoObtido).isNotNull();
            assertThat(produtoObtido.getId()).isEqualTo(id);
            assertThat(produtoObtido.getNome()).isEqualTo(produto.getNome());
        }

        @Test
        void deveGerarExcecao_QuandoBuscarProdutoPorId_NaoExiste() {
            var id = 2L;

            when(produtoRepository.findById(any(Long.class))).thenReturn(Optional.empty());

            assertThatThrownBy(
                    () -> produtoService.getProdutoPorId(id))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessage("Produto não encontrado para o ID: " + id);

            verify(produtoRepository, times(1)).findById(id);
        }
    }

    @Nested
    class AlterarProduto {
        @Test
        void devePermitirAlterarProdutoPorId() {
            var id = 1L;
            var produtoAntigo = ProdutoHelper.gerarProduto();
            produtoAntigo.setId(id);
            var produtoNovo = produtoAntigo;
            produtoNovo.setNome("Meu Teste");

            when(produtoRepository.findById(any(Long.class))).thenReturn(Optional.of(produtoAntigo));

            when(produtoRepository.save(any(Produto.class))).thenAnswer(p -> p.getArgument(0));

            var produtoObtido = produtoService.updateProduto(id, produtoNovo);

            assertThat(produtoObtido)
                    .isInstanceOf(Produto.class)
                    .isNotNull();
            assertThat(produtoObtido.getId()).isEqualTo(produtoNovo.getId());
            assertThat(produtoObtido.getNome()).isEqualTo(produtoNovo.getNome());
            verify(produtoRepository, times(1)).save(any(Produto.class));
        }
    }

    @Nested
    class ExcluirProduto {
        @Test
        void devePermitirExcluirProdutoPorId() {
            var id = 1L;
            var produto = ProdutoHelper.gerarProduto();

            when(produtoRepository.findById(id))
                    .thenReturn(Optional.of(produto));
            doNothing()
                    .when(produtoRepository).deleteById(id);

            var resultado = produtoService.deleteProduto(id);

            assertThat(resultado).isTrue();
            verify(produtoRepository, times(1)).findById(any(Long.class));
            verify(produtoRepository, times(1)).delete(any(Produto.class));
        }
    }
}
