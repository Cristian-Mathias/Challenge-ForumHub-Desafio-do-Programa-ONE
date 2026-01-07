package br.com.challenge.forumhub.controller;

import br.com.challenge.forumhub.domain.dto.DadosTokenJWT;
import br.com.challenge.forumhub.domain.dto.DadosAutenticacao;
import br.com.challenge.forumhub.domain.entity.Usuario;
import br.com.challenge.forumhub.exception.TokenInvalidoException;
import br.com.challenge.forumhub.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(
        name = "Autenticação",
        description = "Endpoint responsável pela autenticação e geração de token JWT"
)
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Operation(
            summary = "Efetuar login",
            description = "Autentica o usuário e retorna um token JWT para acesso aos endpoints protegidos."
    )
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            dados.login(),
                            dados.senha()
                    );

            Authentication authentication =
                    manager.authenticate(authenticationToken);

            Usuario usuario =
                    (Usuario) authentication.getPrincipal();

            String tokenJWT =
                    tokenService.gerarToken(usuario);
            return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
        } catch (UsernameNotFoundException | BadCredentialsException ex) {
            throw new TokenInvalidoException("Login ou senha incorretos");
        }
    }
}
