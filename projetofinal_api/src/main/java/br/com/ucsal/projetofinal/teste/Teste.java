package br.com.ucsal.projetofinal.teste;

import br.com.ucsal.projetofinal.resultado.Resultado;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Teste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean execute;

    private Boolean resultadoFinal;

    private String exception;

    @Column(length = 110000)
    private String entrada;

    @Column(length = 110000)
    private String saidaEsperada;

    @Column(length = 110000)
    private String saidaObtida;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resultado_id")
    @JsonIgnore
    private Resultado resultado;

    public Teste(Boolean execute, Boolean resultadoFinal, String exception, String entrada, String saidaEsperada, String saidaObtida, Resultado resultado) {
        this.execute = execute;
        this.resultadoFinal = resultadoFinal;
        this.exception = exception;
        this.entrada = entrada;
        this.saidaEsperada = saidaEsperada;
        this.saidaObtida = saidaObtida;
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Teste{" +
                "id=" + id +
                ", execute=" + execute +
                ", resultadoFinal=" + resultadoFinal +
                ", exception='" + exception + '\'' +
                ", entrada='" + entrada + '\'' +
                ", saidaEsperada='" + saidaEsperada + '\'' +
                ", saidaObtida='" + saidaObtida + '\'' +
                ", resultado=" + resultado +
                '}';
    }
}
