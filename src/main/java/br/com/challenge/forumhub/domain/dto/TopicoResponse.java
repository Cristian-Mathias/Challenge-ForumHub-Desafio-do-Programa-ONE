package br.com.challenge.forumhub.domain.dto;

import br.com.challenge.forumhub.domain.enums.EstadoTopico;

import java.time.LocalDateTime;

public record TopicoResponse(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        EstadoTopico estado,
        String autor,
        String curso
) {

}
