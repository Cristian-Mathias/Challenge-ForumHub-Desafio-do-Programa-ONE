package br.com.challenge.forumhub.service;

import br.com.challenge.forumhub.domain.dto.TopicoRequest;
import br.com.challenge.forumhub.domain.dto.TopicoResponse;
import br.com.challenge.forumhub.domain.entity.Topico;
import br.com.challenge.forumhub.domain.enums.EstadoTopico;
import br.com.challenge.forumhub.domain.repository.TopicoRepository;
import br.com.challenge.forumhub.exception.TopicoDuplicadoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    private final TopicoRepository repository;

    public TopicoService(TopicoRepository repository) {
        this.repository = repository;
    }

    public TopicoResponse criar(TopicoRequest request){

        if (repository.existsByTituloAndMensagem(request.titulo(), request.mensagem())){
            throw new TopicoDuplicadoException("Já existe um tópico com o mesmo título e mensagem.");
        }


        Topico topico = new Topico();
        topico.setTitulo(request.titulo());
        topico.setMensagem(request.mensagem());
        topico.setAutor(request.autor());
        topico.setCurso(request.curso());
        topico.setEstado(EstadoTopico.ABERTO);

        Topico salvo = repository.save(topico);

        return new TopicoResponse(
                salvo.getId(),
                salvo.getTitulo(),
                salvo.getMensagem(),
                salvo.getDataCriacao(),
                salvo.getEstado(),
                salvo.getAutor(),
                salvo.getCurso()
        );
    }

    public List<TopicoResponse> listar(){
        return repository.findAll()
                .stream()
                .map(t -> new TopicoResponse(
                        t.getId(),
                        t.getTitulo(),
                        t.getMensagem(),
                        t.getDataCriacao(),
                        t.getEstado(),
                        t.getAutor(),
                        t.getCurso()
                ))
                .toList();
    }
}
