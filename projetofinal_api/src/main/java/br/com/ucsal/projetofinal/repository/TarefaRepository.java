package br.com.ucsal.projetofinal.repository;

import br.com.ucsal.projetofinal.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    Optional<Tarefa> findById(Long id);
}
