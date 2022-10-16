package br.com.ucsal.projetofinal.prova;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProvaRepository extends JpaRepository<Prova, Long> {

    @Query(value = "SELECT * FROM PROVA JOIN PROVA_TAREFAS WHERE PROVA_ID = :id", nativeQuery = true)
    List<Prova> listarPorIdProva(Long id);

}
