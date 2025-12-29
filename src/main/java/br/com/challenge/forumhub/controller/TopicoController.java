package br.com.challenge.forumhub.controller;

import br.com.challenge.forumhub.domain.dto.TopicoRequest;
import br.com.challenge.forumhub.domain.dto.TopicoResponse;
import br.com.challenge.forumhub.domain.entity.Topico;
import br.com.challenge.forumhub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService service;

    public TopicoController(TopicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TopicoResponse> criar(@RequestBody @Valid TopicoRequest request){
        TopicoResponse response = service.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
