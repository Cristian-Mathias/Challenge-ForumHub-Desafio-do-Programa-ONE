package br.com.challenge.forumhub.domain.repository;

import br.com.challenge.forumhub.domain.entity.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
