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
@Inheritance(strategy=InheritanceType.JOINED)
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String saidaObtida;

    private Boolean create;

    private Boolean compile;

    private Double porcentagem;

    @NotNull
    @Valid
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resposta_id")
    @JsonBackReference
    private Resposta resposta;

    @ManyToOne
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "feedback_id")
    @JsonBackReference
    private Feedback feedback;

    @Valid
    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "resultado_id")
    private List<Teste> testes = new ArrayList<>();

    public Resultado(String saidaObtida, Boolean create, Boolean compile, Double porcentagem, Resposta resposta, List<Teste> testes) {
        this.saidaObtida = saidaObtida;
        this.create = create;
        this.compile = compile;
        this.porcentagem = porcentagem;
        this.resposta = resposta;
        this.testes = testes;
    }
}
