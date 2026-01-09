package br.com.challenge.forumhub.domain.dto;

import java.time.LocalDateTime;

public record RespostaResponse(
        Long id,
        String mensagem,
        LocalDateTime dataCriacao,
        boolean solucao,
        String autor
) {
}
