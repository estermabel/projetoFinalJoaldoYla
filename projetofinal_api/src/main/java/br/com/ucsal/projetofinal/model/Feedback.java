package br.com.ucsal.projetofinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String complexidade;

    @Valid
    @OneToOne
    private Resposta resposta;

    @Valid
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "resultado_id")
    private List<Resultado> resultados = new ArrayList<>();

    public Feedback(String complexidade, Resposta resposta, List<Resultado> resultados) {
        this.complexidade = complexidade;
        this.resposta = resposta;
        this.resultados = resultados;
    }
}
