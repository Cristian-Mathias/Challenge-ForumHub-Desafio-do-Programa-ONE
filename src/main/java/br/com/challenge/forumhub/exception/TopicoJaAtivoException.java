package br.com.challenge.forumhub.exception;

public class TopicoJaAtivoException extends RuntimeException{
    public TopicoJaAtivoException(){
        super("Tópico já está ativo.");
    }
}
