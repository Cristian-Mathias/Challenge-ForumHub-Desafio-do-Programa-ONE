package br.com.challenge.forumhub.controller;

import br.com.challenge.forumhub.domain.dto.DadosAtualizacaoTopico;
import br.com.challenge.forumhub.domain.dto.TopicoRequest;
import br.com.challenge.forumhub.domain.dto.TopicoResponse;
import br.com.challenge.forumhub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<TopicoResponse>> listarTodosOsTopicos(@PageableDefault(size = 10, sort = "dataCriacao", direction = Sort.Direction.ASC)Pageable paginacao){
        Page<TopicoResponse> page =  service.listarTodosOsTopicos(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<TopicoResponse>> listarPorCursoEAno(
            @RequestParam String curso,
            @RequestParam int ano,
            @PageableDefault(size = 10, sort = "dataCriacao", direction = Sort.Direction.ASC) Pageable paginacao
    ) {
        Page<TopicoResponse> page = service.listarPorCursoEAno(curso,ano,paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponse>buscarPorId(@PathVariable Long id){
        TopicoResponse response = service.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponse> atualizar(@PathVariable Long id , @RequestBody DadosAtualizacaoTopico dados){
        TopicoResponse response = service.atualizar(id, dados);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>excluir(@PathVariable Long id){
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/inativar/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id){
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/ativar/{id}")
    public ResponseEntity<Void> ativar(@PathVariable Long id){
        service.ativar(id);
        return ResponseEntity.noContent().build();
    }
}
