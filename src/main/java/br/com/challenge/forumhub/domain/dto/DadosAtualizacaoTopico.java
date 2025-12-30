package br.com.challenge.forumhub.domain.dto;

public record DadosAtualizacaoTopico(
        String titulo,
        String mensagem,
        String autor,
        String curso
) {
}
