package br.com.challenge.forumhub.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record TopicoRequest(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        String autor,
        String curso
) {}
