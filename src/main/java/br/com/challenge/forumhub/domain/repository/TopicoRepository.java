package br.com.challenge.forumhub.domain.repository;

import br.com.challenge.forumhub.domain.entity.Topico;
import br.com.challenge.forumhub.domain.enums.EstadoTopico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensagem(String titulo, String mensagem);

    @Query("SELECT t FROM Topico t WHERE t.curso = :curso AND YEAR(t.dataCriacao) = :ano AND t.estado = :estado")
    Page<Topico>findByCursoEAno(@Param("curso") String curso, @Param("ano") int ano,@Param("estado") EstadoTopico estado, Pageable pageable);

    Page<Topico> findByEstado(EstadoTopico estado, Pageable pageable);

    Optional<Topico> findByIdAndEstado(Long id , EstadoTopico estado);
}
