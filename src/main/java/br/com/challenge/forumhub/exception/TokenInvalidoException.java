package br.com.challenge.forumhub.exception;

public class TokenInvalidoException extends RuntimeException{
    public TokenInvalidoException(String mensagem){
        super(mensagem);
    }
}
