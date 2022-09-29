package br.com.ucsal.projetofinal.resposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    @Query(value = "SELECT * FROM RESPOSTA WHERE USUARIO_ID = :id", nativeQuery = true)
    List<Resposta> findByUsuarioId(Long id);

    @Query(value = "SELECT * FROM RESPOSTA WHERE TAREFA_ID = :id", nativeQuery = true)
    List<Resposta> findByTarefaId(Long id);

}
