package br.com.challenge.forumhub.exception;

public class TopicoJaInativoException extends RuntimeException{
    public TopicoJaInativoException(){
        super("Tópico já está inativo!");
    }
}
