package br.com.ucsal.projetofinal.repository;

import br.com.ucsal.projetofinal.model.CasoTeste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CasoTesteRepository extends JpaRepository<CasoTeste, Long> {

    Optional<CasoTeste> findById(Long id);
}
