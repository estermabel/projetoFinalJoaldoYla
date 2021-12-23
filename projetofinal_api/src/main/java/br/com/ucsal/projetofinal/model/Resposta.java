package br.com.ucsal.projetofinal.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String codigo;
    @NotNull
    private Instant dataEnvio;

    @Valid
    @NotNull
    @ManyToOne
    private Usuario usuario;

    @Valid
    @NotNull
    @OneToOne
    private Tarefa tarefa;

    public Resposta() {
    }

    public Resposta(Long id, String codigo, Instant dataEnvio, Usuario usuario, Tarefa tarefa) {
        this.id = id;
        this.codigo = codigo;
        this.dataEnvio = dataEnvio;
        this.usuario = usuario;
        this.tarefa = tarefa;
    }
}
