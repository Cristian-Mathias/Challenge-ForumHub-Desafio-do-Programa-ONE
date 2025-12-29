package br.com.challenge.forumhub.domain.repository;

import br.com.challenge.forumhub.domain.entity.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensagem(String titulo, String mensagem);

    @Query("SELECT t FROM Topico t WHERE t.curso = :curso AND EXTRACT(YEAR FROM t.dataCriacao) = :ano")
    Page<Topico>findByCursoEAno(@Param("curso") String curso, @Param("ano") int ano, Pageable pageable);
}
