package br.com.ucsal.projetofinal.tarefa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    @Query("SELECT t FROM Tarefa t WHERE t.id=:id")
    Optional<Tarefa> buscarTarefa(Long id);
}
