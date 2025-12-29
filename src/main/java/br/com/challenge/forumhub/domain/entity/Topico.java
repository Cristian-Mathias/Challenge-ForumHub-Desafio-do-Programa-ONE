package br.com.challenge.forumhub.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
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
    private String estado;
    private String autor;
    private String curso;

    @PrePersist
    public void prePersist(){
        this.dataCriacao = LocalDateTime.now();
    }
}
