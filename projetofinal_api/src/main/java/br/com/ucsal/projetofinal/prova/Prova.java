package br.com.ucsal.projetofinal.prova;

import br.com.ucsal.projetofinal.itemProva.ItemProva;
import br.com.ucsal.projetofinal.tarefa.Tarefa;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Prova {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "prova", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ItemProva> itens = new ArrayList<>();

    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataEntrega;

    public Prova(String nome, List<ItemProva> itens, LocalDateTime dataEntrega) {
        this.nome = nome;
        this.itens = itens;
        this.dataEntrega = dataEntrega;
    }

    public void adicionarItem(ItemProva item){
        item.setProva(this);
        this.itens.add(item);
    }
}
