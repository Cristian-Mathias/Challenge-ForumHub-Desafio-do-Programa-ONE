package br.com.challenge.forumhub.infra.security;

import br.com.challenge.forumhub.domain.dto.erro.ErroPadrao;
import br.com.challenge.forumhub.domain.repository.UsuarioRepository;
import br.com.challenge.forumhub.exception.TokenInvalidoException;
import br.com.challenge.forumhub.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.equals("/login")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.equals("/swagger-ui.html")
                || path.equals("/status");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String tokenJWT = recuperarToken(request);

        if (tokenJWT == null) {
            throw new TokenInvalidoException("Token JWT não fornecido");
        }
        try {
            String subject = tokenService.getSubject(tokenJWT);
            UserDetails usuario = repository.findByLogin(subject);

            if (usuario == null) {
                throw new TokenInvalidoException("Usuário não encontrado para este token");
            }

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            usuario,
                            null,
                            usuario.getAuthorities()
                    );

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);


        } catch (RuntimeException ex) {
            SecurityContextHolder.clearContext();
            throw new TokenInvalidoException("Token JWT inválido ou expirado");
        }


        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null ||
                !authorizationHeader.startsWith("Bearer ")) {
            return null;
        }

        return authorizationHeader.replace("Bearer ", "");
    }
}
