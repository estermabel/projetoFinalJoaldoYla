package br.com.ucsal.projetofinal.repository;

import br.com.ucsal.projetofinal.model.CasoTeste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CasoTesteRepository extends JpaRepository<CasoTeste, Long> {

    @Query(value = "SELECT * FROM CASO_TESTE WHERE TAREFA_ID = :id AND FLAG_EXIBIR = TRUE ", nativeQuery = true)
    List<CasoTeste> findByTarefaId(Long id);

}
