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
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String saidaObtida;

    @NotNull
    private Boolean resultado;

    @OneToOne
    private Resposta resposta;

    @OneToOne
    private CasoTeste casoTeste;

    @Valid
    @ManyToOne
    @JoinColumn(name = "feedback_id")
    @JsonBackReference
    private Feedback feedback;

    public Resultado(String saidaObtida, Boolean resultado, Resposta resposta, CasoTeste casoTeste) {
        this.saidaObtida = saidaObtida;
        this.resultado = resultado;
        this.resposta = resposta;
        this.casoTeste = casoTeste;
    }
}
