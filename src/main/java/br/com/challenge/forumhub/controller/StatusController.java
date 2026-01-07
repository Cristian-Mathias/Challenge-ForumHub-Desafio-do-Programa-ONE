package br.com.challenge.forumhub.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(
        name = "Status",
        description = "Endpoint público para verificação de disponibilidade da API"
)
public class StatusController {

    @GetMapping("/status")
    @Operation(
            summary = "Verificar status da API",
            description = "Endpoint público que indica se a API FórumHub está em execução."
    )
    public String status(){
        return "API FórumHub está rodando e o acesso público está liberado.";
    }
}
