package br.com.challenge.forumhub.service;

import br.com.challenge.forumhub.domain.dto.UsuarioResponse;
import br.com.challenge.forumhub.domain.entity.Usuario;
import br.com.challenge.forumhub.domain.repository.UsuarioRepository;
import br.com.challenge.forumhub.exception.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponse usuarioLogado(String login) {
        Usuario usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));

        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getPerfil()
        );
    }
}
