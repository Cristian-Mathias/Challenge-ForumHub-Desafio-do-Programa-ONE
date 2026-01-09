package br.com.challenge.forumhub.controller;

import br.com.challenge.forumhub.domain.dto.RespostaRequest;
import br.com.challenge.forumhub.domain.dto.RespostaResponse;
import br.com.challenge.forumhub.domain.entity.Usuario;
import br.com.challenge.forumhub.service.RespostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respostas")
@Tag(name = "Respostas", description = "Gerenciamento das respostas dos tópicos")
public class RespostaController {

    @Autowired
    private final RespostaService respostaService;

    public RespostaController(RespostaService respostaService) {
        this.respostaService = respostaService;
    }

    @PostMapping
    @Operation(summary = "Criar resposta", description = "Cria uma resposta para um tópico ativo")
    public ResponseEntity<RespostaResponse> criar(@RequestBody @Valid RespostaRequest request, Authentication authentication) throws Throwable {
        String loginUsuario = authentication.getName();

        RespostaResponse resposta = respostaService.criar(request,loginUsuario);
        return  ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @PatchMapping("/{id}/solucao")
    @Operation(summary = "Marcar resposta como solução")
    public ResponseEntity<Void> marcarSolucao(@PathVariable Long id, Authentication authentication) {
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        respostaService.marcarComoSolucao(id,usuarioLogado );
        return ResponseEntity.noContent().build();
    }

}
