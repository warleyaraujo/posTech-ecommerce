package com.postech.crmservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("https://app-gestaoclientes.azurewebsites.net");
        devServer.setDescription("Server Production");

        Server prodServer = new Server();
        prodServer.setUrl("https://localhost:8090");
        prodServer.setDescription("Server Development");

        Contact contact = new Contact();
        contact.setEmail("contato@gmail.com");
        contact.setName("Contato");

        Info info = new Info()
                .title("Tech Challenge Fase IV")
                .version("1.0")
                .contact(contact)
                .description("Documentação dos endpoints da fase IV - Microserviços");


        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
