package br.com.challenge.forumhub.domain.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "respostas")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;
    @ManyToOne
    @JoinColumn(name = "topico_id", nullable = false)
    private Topico topico;
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private boolean solucao = false;

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isSolucao() {
        return solucao;
    }

    public void setSolucao(boolean solucao) {
        this.solucao = solucao;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

}


