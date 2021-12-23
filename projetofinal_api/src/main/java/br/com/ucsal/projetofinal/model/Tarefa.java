package br.com.ucsal.projetofinal.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataEntrega;

    @Valid
    @OneToMany
    private List<CasoTeste> testes = new ArrayList<>();

    public Tarefa() {

    }

    public Tarefa(Long id, String titulo, String descricao, LocalDateTime dataEntrega, List<CasoTeste> testes) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataEntrega = dataEntrega;
        this.testes = testes;
    }
}
