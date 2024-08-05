package com.postech.crmservice.service;

import com.postech.crmservice.entities.Cliente;
import com.postech.crmservice.entities.DTOs.ClienteDto;
import com.postech.crmservice.mappers.ClienteMapper;
import com.postech.crmservice.mappers.ClienteMapperImpl;
import com.postech.crmservice.mappers.EnderecoMapperImpl;
import com.postech.crmservice.repositories.ClienteRepository;
import com.postech.crmservice.services.ClienteService;
import com.postech.crmservice.services.ClienteServiceImpl;
import com.postech.crmservice.utils.ClienteHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapperImpl clienteMapper;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {

        openMocks = MockitoAnnotations.openMocks(this);
        clienteService = new ClienteServiceImpl(clienteRepository, clienteMapper);
    }

    @AfterEach
    void tearDow() throws Exception {
        openMocks.close();
    }

    @Nested
    class RegistrarCliente {
        @Test
        void deveRegistrarCliente() {
            var cliente = ClienteHelper.gerarRegistro();
            var clienteDto = ClienteHelper.gerarRegistroDto();

            when(clienteMapper.toEntity(clienteDto))
                    .thenReturn(cliente);
            when(clienteMapper.toDto(cliente))
                    .thenReturn(clienteDto);

            when(clienteRepository.save(cliente))
                    .thenAnswer(i -> i.getArgument(0));

            var clienteRegistrado = clienteService.save(clienteDto);
            assertThat(clienteRegistrado).isInstanceOf(ClienteDto.class).isNotNull();
            assertThat(clienteRegistrado.nome()).isEqualTo(cliente.getNome());
            assertThat(clienteRegistrado.email()).isEqualTo(cliente.getEmail());
            assertThat(clienteRegistrado.telefone()).isEqualTo(cliente.getTelefone());
            verify(clienteRepository, times(1)).save(any(Cliente.class));
        }

    }

    @Nested
    class RemoverCliente {
        @Test
        void deveRemoverCliente() {
            var cliente = ClienteHelper.gerarRegistro();
            var clienteDto = ClienteHelper.gerarRegistroDto();

            when(clienteRepository.findById(cliente.getId()))
                    .thenReturn(Optional.of(cliente));
            when(clienteMapper.toDto(cliente))
                    .thenReturn(clienteDto);

            clienteService.delete(cliente.getId());

            verify(clienteRepository, times(1)).deleteById(cliente.getId());
        }

        @Test
        void deveRetornarErroAoRemoverClienteInexistente() {
            var cliente = ClienteHelper.gerarRegistro();
            var clienteDto = ClienteHelper.gerarRegistroDto();

            when(clienteRepository.findById(cliente.getId()))
                    .thenReturn(Optional.empty());
            when(clienteMapper.toDto(cliente))
                    .thenReturn(clienteDto);

            try {
                clienteService.delete(cliente.getId());
            } catch (Exception e) {
                assertThat(e).isInstanceOf(Exception.class);
            }
        }

        @Test
        void deveRetornarErroAoRemoverClienteComIdNulo() {
            var cliente = ClienteHelper.gerarRegistro();
            var clienteDto = ClienteHelper.gerarRegistroDto();

            when(clienteRepository.findById(cliente.getId()))
                    .thenReturn(Optional.empty());
            when(clienteMapper.toDto(cliente))
                    .thenReturn(clienteDto);

            try {
                clienteService.delete(null);
            } catch (Exception e) {
                assertThat(e).isInstanceOf(Exception.class);
            }
        }
    }

    @Nested
    class AtualizarCliente {
        @Test
        void deveAtualizarCliente() {
            var cliente = ClienteHelper.gerarRegistro();
            var clienteDto = ClienteHelper.gerarRegistroDto();

            when(clienteRepository.findById(cliente.getId()))
                    .thenReturn(Optional.of(cliente));
            when(clienteMapper.toDto(cliente))
                    .thenReturn(clienteDto);

            clienteService.update(cliente.getId(), clienteDto);

            verify(clienteRepository, times(1)).save(any(Cliente.class));
        }

        @Test
        void deveRetornarErroAoAtualizarClienteInexistente() {
            var cliente = ClienteHelper.gerarRegistro();
            var clienteDto = ClienteHelper.gerarRegistroDto();

            when(clienteRepository.findById(cliente.getId()))
                    .thenReturn(Optional.empty());
            when(clienteMapper.toDto(cliente))
                    .thenReturn(clienteDto);

            try {
                clienteService.update(cliente.getId(), clienteDto);
            } catch (Exception e) {
                assertThat(e).isInstanceOf(Exception.class);
            }
        }

        @Test
        void deveRetornarErroAoAtualizarClienteComIdNulo() {
            var cliente = ClienteHelper.gerarRegistro();
            var clienteDto = ClienteHelper.gerarRegistroDto();

            when(clienteRepository.findById(cliente.getId()))
                    .thenReturn(Optional.empty());
            when(clienteMapper.toDto(cliente))
                    .thenReturn(clienteDto);

            try {
                clienteService.update(null, clienteDto);
            } catch (Exception e) {
                assertThat(e).isInstanceOf(Exception.class);
            }
        }
    }

    @Nested
    class BuscarCliente {
        @Test
        void deveBuscarCliente() {
            var cliente = ClienteHelper.gerarRegistro();
            var clienteDto = ClienteHelper.gerarRegistroDto();

            when(clienteRepository.findById(cliente.getId()))
                    .thenReturn(Optional.of(cliente));
            when(clienteMapper.toDto(cliente))
                    .thenReturn(clienteDto);

            var clienteObtido = clienteService.getById(cliente.getId());

            assertThat(clienteObtido).isInstanceOf(ClienteDto.class).isNotNull();
            assertThat(clienteObtido.nome()).isEqualTo(cliente.getNome());
            assertThat(clienteObtido.email()).isEqualTo(cliente.getEmail());
            assertThat(clienteObtido.telefone()).isEqualTo(cliente.getTelefone());
            verify(clienteRepository, times(1)).findById(cliente.getId());
        }

        @Test
        void deveRetornarErroAoBuscarClienteInexistente() {
            var cliente = ClienteHelper.gerarRegistro();
            var clienteDto = ClienteHelper.gerarRegistroDto();

            when(clienteRepository.findById(cliente.getId()))
                    .thenReturn(Optional.empty());
            when(clienteMapper.toDto(cliente))
                    .thenReturn(clienteDto);

            try {
                clienteService.getById(cliente.getId());
            } catch (Exception e) {
                assertThat(e).isInstanceOf(Exception.class);
            }
        }

        @Test
        void deveRetornarErroAoBuscarClienteComIdNulo() {
            var cliente = ClienteHelper.gerarRegistro();
            var clienteDto = ClienteHelper.gerarRegistroDto();

            when(clienteRepository.findById(cliente.getId()))
                    .thenReturn(Optional.empty());
            when(clienteMapper.toDto(cliente))
                    .thenReturn(clienteDto);

            try {
                clienteService.getById(null);
            } catch (Exception e) {
                assertThat(e).isInstanceOf(Exception.class);
            }
        }
    }

    @Nested
    class ListarClientes {
        @Test
        void deveListarClientes() {
            var cliente = ClienteHelper.gerarRegistro();
            var clienteDto = ClienteHelper.gerarRegistroDto();

            when(clienteRepository.findAll())
                    .thenReturn(List.of(cliente));
            when(clienteMapper.toDto(cliente))
                    .thenReturn(clienteDto);

            var clientes = clienteService.getAll();

            assertThat(clientes).isInstanceOf(List.class).isNotNull();
            assertThat(clientes.size()).isEqualTo(1);
            assertThat(clientes.get(0).nome()).isEqualTo(cliente.getNome());
            assertThat(clientes.get(0).email()).isEqualTo(cliente.getEmail());
            assertThat(clientes.get(0).telefone()).isEqualTo(cliente.getTelefone());
            verify(clienteRepository, times(1)).findAll();
        }

        @Test
        void deveRetornarErroAoListarClientesVazio() {
            when(clienteRepository.findAll())
                    .thenReturn(List.of());
            try {
                clienteService.getAll();
            } catch (Exception e) {
                assertThat(e).isInstanceOf(Exception.class);
            }
        }
    }
}
