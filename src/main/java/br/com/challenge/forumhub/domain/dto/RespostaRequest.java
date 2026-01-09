package br.com.challenge.forumhub.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RespostaRequest(
        @NotBlank String mensagem,
        @NotNull Long topicoId
)
{
}
