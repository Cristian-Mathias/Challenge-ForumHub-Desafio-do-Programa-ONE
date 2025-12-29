package br.com.challenge.forumhub.domain.entity;

import br.com.challenge.forumhub.domain.enums.EstadoTopico;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topicos")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String mensagem;
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;
    @Column(name = "estado_topico", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoTopico estado;
    private String autor;
    private String curso;

    public String getAutor() {
        return autor;
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

    public void setAutor(String autor) {
        this.autor = autor;
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

    @PrePersist
    public void prePersist(){
        this.dataCriacao = LocalDateTime.now();
    }
}
