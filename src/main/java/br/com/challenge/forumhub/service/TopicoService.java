package br.com.challenge.forumhub.service;

import br.com.challenge.forumhub.domain.dto.DadosAtualizacaoTopico;
import br.com.challenge.forumhub.domain.dto.TopicoRequest;
import br.com.challenge.forumhub.domain.dto.TopicoResponse;
import br.com.challenge.forumhub.domain.entity.Topico;
import br.com.challenge.forumhub.domain.entity.Usuario;
import br.com.challenge.forumhub.domain.enums.EstadoTopico;
import br.com.challenge.forumhub.domain.repository.TopicoRepository;
import br.com.challenge.forumhub.exception.RecursoNaoEncontradoException;
import br.com.challenge.forumhub.exception.RegraNegocioException;
import br.com.challenge.forumhub.exception.TopicoDuplicadoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class TopicoService {

    private final TopicoRepository repository;

    public TopicoService(TopicoRepository repository) {
        this.repository = repository;
    }

    public TopicoResponse criar(TopicoRequest request, Usuario usuarioLogado){

        if (repository.existsByTituloAndMensagem(request.titulo(), request.mensagem())){
            throw new TopicoDuplicadoException("Já existe um tópico com o mesmo título e mensagem!");
        }


        Topico topico = new Topico();
        topico.setTitulo(request.titulo());
        topico.setMensagem(request.mensagem());
        topico.setAutor(usuarioLogado);
        topico.setCurso(request.curso());
        topico.setEstado(EstadoTopico.ATIVO);

        Topico salvo = repository.save(topico);

        return new TopicoResponse(
                salvo.getId(),
                salvo.getTitulo(),
                salvo.getMensagem(),
                salvo.getDataCriacao(),
                salvo.getEstado(),
                salvo.getAutor().getNome(),
                salvo.getCurso()
        );
    }

    public Page<TopicoResponse> listarTodosOsTopicos(Pageable paginacao) {
        return repository.findByEstado(EstadoTopico.ATIVO, paginacao)
                .map(t -> new TopicoResponse(
                        t.getId(),
                        t.getTitulo(),
                        t.getMensagem(),
                        t.getDataCriacao(),
                        t.getEstado(),
                        t.getAutor().getNome(),
                        t.getCurso()
                ));
    }

    public Page<TopicoResponse> listarPorCursoEAno(String curso, int ano, Pageable paginacao) {
        Page<Topico> page = repository.findByCursoEAno(curso, ano,EstadoTopico.ATIVO ,paginacao);

                return page.map(t -> new TopicoResponse(
                        t.getId(),
                        t.getTitulo(),
                        t.getMensagem(),
                        t.getDataCriacao(),
                        t.getEstado(),
                        t.getAutor().getNome(),
                        t.getCurso()
                ));
    }

    public TopicoResponse buscarPorId(Long id){
        Topico topico = repository.findByIdAndEstado(id,EstadoTopico.ATIVO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tópico não encontrado!"));

        return new TopicoResponse(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getEstado(),
                topico.getAutor().getNome(),
                topico.getCurso()
        );
    }


    public TopicoResponse atualizar(Long id , DadosAtualizacaoTopico dados, Usuario usuarioLogado) {

        Optional<Topico> optionalTopico = repository.findById(id);

        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();

            if(topico.getEstado() == EstadoTopico.INATIVO){
                throw new RegraNegocioException("Tópico inativo não pode ser atualizado");
            }


            topico.setTitulo(dados.titulo());
            topico.setMensagem(dados.mensagem());
            topico.setAutor(usuarioLogado);
            topico.setCurso(dados.curso());

            repository.save(topico);

            return new TopicoResponse(
                    topico.getId(),
                    topico.getTitulo(),
                    topico.getMensagem(),
                    topico.getDataCriacao(),
                    topico.getEstado(),
                    topico.getAutor().getNome(),
                    topico.getCurso()
            );
        }
        throw  new RecursoNaoEncontradoException("Tópico não encontrado para atualização!");
    }

    public void excluir(Long id){
        Optional<Topico> optionalTopico = repository.findById(id);

        if(optionalTopico.isPresent()){
            repository.deleteById(id);
        }else {
            throw new RecursoNaoEncontradoException("Tópico não encontrado para exclusão!");
        }
    }

    @Transactional
    public void inativar(Long id){
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tópico não encontrado para ser inativado!"));

        topico.inativar();
    }

    @Transactional
    public void ativar(Long id){
        Topico topico =repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tópico não encontrado para ser inativado!"));
        topico.ativar();
    }
}
