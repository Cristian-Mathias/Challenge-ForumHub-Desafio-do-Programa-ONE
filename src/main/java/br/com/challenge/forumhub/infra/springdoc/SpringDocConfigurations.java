package br.com.challenge.forumhub.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                )
                .addSecurityItem(
                        new SecurityRequirement().addList("bearerAuth")
                )
                .info(new Info()
                        .title("FórumHub API")
                        .description("""
                                FórumHub é uma API REST desenvolvida em Java com Spring Boot,
                                criada como parte do Challenge Back End da Alura.
                                O projeto implementa um CRUD completo de tópicos de fórum, seguindo boas práticas REST,
                                com validações de negócio, autenticação/autorização e persistência em banco de dados relacionais.
                                """)
                        .contact(new Contact()
                                .name("Challenge Back-end Alura")
                                .email("seu-contato-aqui"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("url-licença")));

    }

}
