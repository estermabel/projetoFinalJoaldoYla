package br.com.ucsal.projetofinal.repository;

import br.com.ucsal.projetofinal.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
