package br.com.challenge.forumhub.domain.dto.erro;

import java.time.LocalDateTime;

public record ErroPadrao(
        int status,
        String mensagem,
        String path,
        LocalDateTime timestamp

) {
}
