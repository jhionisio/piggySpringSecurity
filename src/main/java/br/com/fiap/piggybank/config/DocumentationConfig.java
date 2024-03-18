package br.com.fiap.piggybank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class DocumentationConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("PiggyBank API")
                    .description("Uma API para o sistema de controle de gastos pessoais.")
                    .summary("Essa api server para......")
                    .version("V1")
                    .contact(new Contact()
                        .name("Joao Carlos")
                        .email("joao@fiap.com.br")
                    )
                    .license(new License()
                        .name("MIT Open Soucer")
                        .url("http://piggybank.com/licenca")
                    )
                )
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                                        .bearerFormat("JWT")));
    }

}
