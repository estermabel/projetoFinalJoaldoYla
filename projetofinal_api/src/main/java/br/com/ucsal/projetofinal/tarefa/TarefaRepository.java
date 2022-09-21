package br.com.ucsal.projetofinal.tarefa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    @Query(value = "SELECT * from tarefa inner join caso_teste on tarefa.id = caso_teste.tarefa_id where tarefa.id = :id", nativeQuery = true)
    Optional<Tarefa> findById(Long id);
}
