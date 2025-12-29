package br.com.challenge.forumhub.exception;

public class TopicoDuplicadoException extends RuntimeException{
    public TopicoDuplicadoException(String mensagem) {
        super(mensagem);
    }
}
