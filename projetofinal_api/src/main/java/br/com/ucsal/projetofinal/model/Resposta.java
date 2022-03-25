package br.com.ucsal.projetofinal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String codigo;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataEnvio;

    @Valid
    @NotNull
    @ManyToOne
    private Usuario usuario;

    @Valid
    @NotNull
    @OneToOne
    private Tarefa tarefa;

    @OneToOne
    private Resultado resultado;

    public Resposta(String codigo, LocalDateTime dataEnvio, Usuario usuario, Tarefa tarefa) {
        this.codigo = codigo;
        this.dataEnvio = LocalDateTime.now();
        this.usuario = usuario;
        this.tarefa = tarefa;
    }
}
