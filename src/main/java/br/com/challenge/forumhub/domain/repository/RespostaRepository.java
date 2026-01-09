package br.com.challenge.forumhub.domain.repository;

import br.com.challenge.forumhub.domain.entity.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {
}
