package br.com.challenge.forumhub.controller;

import br.com.challenge.forumhub.domain.dto.UsuarioResponse;
import br.com.challenge.forumhub.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/me")
    @Operation(summary = "Usuário logado", description = "Retorna os dados do usuário autenticado")
    public ResponseEntity<UsuarioResponse> me(Authentication authentication) {
        String loginUsuario = authentication.getName();
        return ResponseEntity.ok(usuarioService.usuarioLogado(loginUsuario));
    }
}
