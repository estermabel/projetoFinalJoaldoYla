package br.com.ucsal.projetofinal.itemProva;

import br.com.ucsal.projetofinal.tarefa.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemProvaRespository extends JpaRepository<ItemProva, Long> {
    @Query("SELECT ip FROM ItemProva ip inner join Tarefa t on ip.tarefa.id = t.id  where ip.prova.id = :idProva")
    List<ItemProva> findByProva(Long idProva);
}
