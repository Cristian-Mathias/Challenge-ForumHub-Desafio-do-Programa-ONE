package br.com.challenge.forumhub.service;

import br.com.challenge.forumhub.domain.dto.RespostaRequest;
import br.com.challenge.forumhub.domain.dto.RespostaResponse;
import br.com.challenge.forumhub.domain.entity.Resposta;
import br.com.challenge.forumhub.domain.entity.Topico;
import br.com.challenge.forumhub.domain.entity.Usuario;
import br.com.challenge.forumhub.domain.repository.RespostaRepository;
import br.com.challenge.forumhub.domain.repository.TopicoRepository;
import br.com.challenge.forumhub.domain.repository.UsuarioRepository;
import br.com.challenge.forumhub.exception.RecursoNaoEncontradoException;
import br.com.challenge.forumhub.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RespostaService {

    @Autowired
    private final TopicoRepository topicoRepository;
    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final RespostaRepository respostaRepository;

    public RespostaService(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, RespostaRepository respostaRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.respostaRepository = respostaRepository;
    }

    public RespostaResponse criar(RespostaRequest request, String loginUsuario) throws Throwable {
        Topico topico = topicoRepository.findById(request.topicoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tópico não encontrado"));

        if (topico.isInativo()){
            throw new RegraNegocioException("Tópico inativo não pode receber respostas");
        }

        Usuario autor = usuarioRepository.findByLogin(loginUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));


        Resposta resposta = new Resposta();
        resposta.setMensagem(request.mensagem());
        resposta.setTopico(topico);
        resposta.setAutor(autor);

        respostaRepository.save(resposta);

        return new RespostaResponse(
                resposta.getId(),
                resposta.getMensagem(),
                resposta.getDataCriacao(),
                resposta.isSolucao(),
                autor.getNome()
        );
    }

    public void marcarComoSolucao(Long respostaId, Usuario usuarioLogado) {

        Resposta resposta = respostaRepository.findById(respostaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Resposta não encontrada"));

        Topico topico = resposta.getTopico();

        if (topico.isInativo()) {
            throw new RegraNegocioException("Não é possível marcar solução em tópico inativo");
        }

        if (!topico.getAutor().getId().equals(usuarioLogado.getId())) {
            throw new RegraNegocioException("Apenas o autor do tópico pode marcar a solução");
        }

        topico.getRespostas().forEach(r -> r.setSolucao(false));
        resposta.setSolucao(true);
        respostaRepository.save(resposta);
    }
}
