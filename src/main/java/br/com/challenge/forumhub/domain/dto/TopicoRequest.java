package br.com.challenge.forumhub.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record TopicoRequest(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {}
