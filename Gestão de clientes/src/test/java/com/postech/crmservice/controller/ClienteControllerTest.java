package com.postech.crmservice.controller;

import com.callibrity.logging.test.LogTracker;
import com.callibrity.logging.test.LogTrackerStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.crmservice.controllers.ClienteController;
import com.postech.crmservice.entities.DTOs.ClienteDto;
import com.postech.crmservice.entities.DTOs.EnderecoDto;
import com.postech.crmservice.handler.GlobalExceptionHandler;
import com.postech.crmservice.services.ClienteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import com.postech.crmservice.utils.ClienteHelper;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ClienteControllerTest {

    private MockMvc mockMvc;

    @RegisterExtension
    LogTrackerStub logTracker = LogTrackerStub.create().recordForLevel(LogTracker.LogLevel.INFO)
            .recordForType(ClienteController.class);

    @Mock
    private ClienteService clienteService;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        ClienteController clienteController = new ClienteController(clienteService);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }
    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Nested
    class RegistrarCliente {
        @Test
        void devePermitirCadastrarCliente() throws Exception {
            var clienteRequest = ClienteHelper.gerarRegistroRequest();
            when(clienteService.save(any(ClienteDto.class)))
                    .thenAnswer(i -> i.getArgument(0));

            mockMvc.perform(post("/clientes")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(clienteRequest)))
                    .andExpect(status().isCreated());

            verify(clienteService, times(1))
                    .save(any(ClienteDto.class));
        }
    }

    @Nested
    class BuscarCliente{

        @Test
        void devePermetirBuscarClientePorId() throws Exception {
            var id = 1L;
            var cliente = new ClienteDto(id, "Teste", "teste", "teste", "teste",
                    null, true, null);

            when(clienteService.getById(any(Long.class))).thenReturn(any(ClienteDto.class));

            mockMvc.perform(get("/clientes/{id}", id)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class AlterarCliente{

        @Test
        void devePermetirAlterarClientePorId() throws Exception {
            var id = 1L;
            var cliente = ClienteHelper.gerarRegistroRequest();

            when(clienteService.update(any(Long.class), any(ClienteDto.class))).thenReturn(any(ClienteDto.class));

            mockMvc.perform(get("/clientes/{id}", id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(cliente)))
                    .andDo(print())
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class ExcluirCliente{
        @Test
        void devePermetirExcluirClientePorId() throws Exception {

            var id = 1L;
            when(clienteService.delete(any(Long.class)))
                    .thenReturn(true);

            mockMvc.perform(delete("/clientes/{id}", id))
                    .andExpect(status().isOk());

            verify(clienteService, times(1)).delete(any(Long.class));
        }
    }

    @Nested
    class ListarClientes{
        @Test
        void devePermetirListarClientes() throws Exception {
            var id = 1L;
            var cliente = new ClienteDto(id, "Teste", "teste", "teste", "teste",
                    null, true, null);

            when(clienteService.getAll()).thenReturn(Collections.singletonList(cliente));

            mockMvc.perform(get("/clientes")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

            verify(clienteService, times(1)).getAll();
        }
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
