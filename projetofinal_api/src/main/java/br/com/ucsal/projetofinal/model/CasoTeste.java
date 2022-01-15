package br.com.ucsal.projetofinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CasoTeste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nomeTeste;

    private String entrada;

    private String saida;

    @NotNull
    private Integer comparacao;

    @Valid
    @ManyToOne
    @JoinColumn(name="tarefa_id")
    @JsonBackReference
    private Tarefa tarefa;

    public CasoTeste(String nomeTeste, String entrada, String saida, Integer comparacao, Tarefa tarefa) {
        this.nomeTeste = nomeTeste;
        this.entrada = entrada;
        this.saida = saida;
        this.comparacao = comparacao;
        this.tarefa = tarefa;
    }
}
