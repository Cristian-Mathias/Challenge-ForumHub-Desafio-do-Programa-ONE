package br.com.challenge.forumhub.domain.dto;

import br.com.challenge.forumhub.domain.enums.Perfil;

public record UsuarioResponse(
        Long id,
        String nome,
        String login,
        Perfil perfil
) {
}
