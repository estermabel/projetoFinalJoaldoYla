package br.com.ucsal.projetofinal.repository;

import br.com.ucsal.projetofinal.model.CasoTeste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CasoTesteRepository extends JpaRepository<CasoTeste, Long> {

}
