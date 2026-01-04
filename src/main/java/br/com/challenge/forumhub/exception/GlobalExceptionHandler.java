package br.com.challenge.forumhub.exception;

import br.com.challenge.forumhub.domain.dto.erro.ErroPadrao;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TopicoDuplicadoException.class)
    public ResponseEntity<ErroPadrao>tratarTopicoDuplicado(TopicoDuplicadoException exception, HttpServletRequest request){
        ErroPadrao erro = new ErroPadrao(
                HttpStatus.CONFLICT.value(),
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()

        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroPadrao>tratarRecursoNaoEncontrado(RecursoNaoEncontradoException exception , HttpServletRequest request){
        ErroPadrao erro = new ErroPadrao(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(TopicoJaAtivoException.class)
    public ResponseEntity<ErroPadrao> tratarTopicoJaAtivo(
            TopicoJaAtivoException exception,
            HttpServletRequest request) {

        ErroPadrao erro = new ErroPadrao(
                HttpStatus.CONFLICT.value(),
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(TopicoJaInativoException.class)
    public ResponseEntity<ErroPadrao> tratarTopicoJaInativo(
            TopicoJaInativoException exception,
            HttpServletRequest request) {

        ErroPadrao erro = new ErroPadrao(
                HttpStatus.CONFLICT.value(),
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }
}
