package com.postech.gestaodeenvio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication(scanBasePackages = "com.postech.gestaodeenvio")
public class GestaoDeEnvioApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestaoDeEnvioApplication.class, args);
    }

}
