package br.com.ucsal.projetofinal.itemProva;

import br.com.ucsal.projetofinal.prova.Prova;
import br.com.ucsal.projetofinal.resposta.Resposta;
import br.com.ucsal.projetofinal.tarefa.Tarefa;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ItemProva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ordem;

    @ManyToOne
    @JsonBackReference
    private Prova prova;

    @ManyToOne
    private Tarefa tarefa;

    public ItemProva(Integer ordem, Prova prova, Tarefa tarefa) {
        this.ordem = ordem;
        this.prova = prova;
        this.tarefa = tarefa;
    }
}
