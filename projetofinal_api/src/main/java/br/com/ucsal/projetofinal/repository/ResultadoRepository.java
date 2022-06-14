package br.com.ucsal.projetofinal.repository;

import br.com.ucsal.projetofinal.model.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResultadoRepository extends JpaRepository<Resultado, Long> {

    @Query(value = "SELECT * FROM RESULTADO WHERE RESPOSTA_ID = :id", nativeQuery = true)
    List<Resultado> findByRespotaId(Long id);
}
