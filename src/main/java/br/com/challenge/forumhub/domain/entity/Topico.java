package br.com.challenge.forumhub.domain.entity;

import br.com.challenge.forumhub.domain.enums.EstadoTopico;
import br.com.challenge.forumhub.exception.TopicoJaAtivoException;
import br.com.challenge.forumhub.exception.TopicoJaInativoException;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "topicos")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String mensagem;
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @Column(name = "estado_topico", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoTopico estado;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario autor;
    private String curso;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Resposta> respostas = new ArrayList<>();

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getCurso() {
        return curso;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public EstadoTopico getEstado() {
        return estado;
    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getTitulo() {
        return titulo;
    }



    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setEstado(EstadoTopico estado) {
        this.estado = estado;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

    public boolean isAtivo(){
        return this.estado == EstadoTopico.ATIVO;
    }

    public boolean isInativo(){
        return this.estado == EstadoTopico.INATIVO;
    }

    public void ativar(){
        if(this.estado == EstadoTopico.ATIVO){
            throw new TopicoJaAtivoException();
        }
        this.estado = EstadoTopico.ATIVO;
    }

    public void inativar(){
        if (this.estado == EstadoTopico.INATIVO){
            throw new TopicoJaInativoException();
        }
        this.estado = EstadoTopico.INATIVO;
    }
}
