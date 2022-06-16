package br.com.ucsal.projetofinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String saidaObtida;

    private Boolean resultado;

    @NotNull
    @Valid
    @OneToOne
    @JoinColumn(name = "resposta_id")
    @JsonBackReference
    private Resposta resposta;

    @OneToOne
    private CasoTeste casoTeste;

    @ManyToOne
    @OneToMany(cascade = CascadeType.ALL)
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
