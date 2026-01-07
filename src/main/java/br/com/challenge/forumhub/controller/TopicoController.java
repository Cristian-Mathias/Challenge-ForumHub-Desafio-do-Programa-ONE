package br.com.challenge.forumhub.controller;

import br.com.challenge.forumhub.domain.dto.DadosAtualizacaoTopico;
import br.com.challenge.forumhub.domain.dto.TopicoRequest;
import br.com.challenge.forumhub.domain.dto.TopicoResponse;
import br.com.challenge.forumhub.service.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(path ="/topicos")
@Tag(
        name = "Tópicos",
        description = "Endpoints responsáveis pelo gerenciamento de tópicos do fórum"
)
public class TopicoController {

    private final TopicoService service;

    public TopicoController(TopicoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(
            summary = "Criar tópico",
            description = "Cria um novo tópico no fórum. Não é permitido cadastrar tópicos duplicados."
    )
    public ResponseEntity<TopicoResponse> criar(@RequestBody @Valid TopicoRequest request){
        TopicoResponse response = service.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(
            summary = "Listar tópicos",
            description = "Lista todos os tópicos ativos de forma paginada."
    )
    public ResponseEntity<Page<TopicoResponse>> listarTodosOsTopicos( @Parameter(hidden = true) @PageableDefault(size = 10, sort = "dataCriacao", direction = Sort.Direction.ASC)Pageable paginacao){
        Page<TopicoResponse> page =  service.listarTodosOsTopicos(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/buscar")
    @Operation(
            summary = "Buscar tópicos por curso e ano",
            description = "Retorna tópicos ativos filtrando por curso e ano de criação."
    )
    public ResponseEntity<Page<TopicoResponse>> listarPorCursoEAno(
            @RequestParam String curso,
            @RequestParam int ano,
            @PageableDefault(size = 10, sort = "dataCriacao", direction = Sort.Direction.ASC) Pageable paginacao
    ) {
        Page<TopicoResponse> page = service.listarPorCursoEAno(curso,ano,paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar tópico por ID",
            description = "Retorna um tópico ativo pelo seu identificador. Tópicos inativos não são retornados."
    )
    public ResponseEntity<TopicoResponse>buscarPorId(@PathVariable Long id){
        TopicoResponse response = service.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar tópico",
            description = "Atualiza completamente os dados de um tópico ativo."
    )
    public ResponseEntity<TopicoResponse> atualizar(@PathVariable Long id , @RequestBody DadosAtualizacaoTopico dados){
        TopicoResponse response = service.atualizar(id, dados);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Excluir tópico",
            description = "Remove um tópico de física."
    )
    public ResponseEntity<Void>excluir(@PathVariable Long id){
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/inativar/{id}")
    @Operation(
            summary = "Inativar tópico",
            description = "Inativa um tópico ativo."
    )
    public ResponseEntity<Void> inativar(@PathVariable Long id){
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/ativar/{id}")
    @Operation(
            summary = "Ativar tópico",
            description = "Ativa um tópico inativo."
    )
    public ResponseEntity<Void> ativar(@PathVariable Long id){
        service.ativar(id);
        return ResponseEntity.noContent().build();
    }
}
