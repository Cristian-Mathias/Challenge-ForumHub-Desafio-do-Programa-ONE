package br.com.challenge.forumhub.controller;

import br.com.challenge.forumhub.domain.dto.TopicoRequest;
import br.com.challenge.forumhub.domain.dto.TopicoResponse;
import br.com.challenge.forumhub.domain.entity.Topico;
import br.com.challenge.forumhub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/topicos")
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

    @GetMapping
    public List<TopicoResponse> listar(){
        return service.listar();
    }
}
