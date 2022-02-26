package br.com.ucsal.projetofinal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    @NotNull
    private Integer perfil;

    @NotNull
    private Boolean flagAtivo;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "UTC-2")
    private Instant dataCriacao;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "UTC-2")
    private Instant dataUltimoAcesso;

    public Usuario(String nome, String login, String senha, Integer perfil, Boolean flagAtivo) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
        this.flagAtivo = flagAtivo;
        ZoneId brazilZone = ZoneId.of("America/Sao_Paulo");
        this.dataCriacao = LocalDateTime.now(brazilZone).toInstant(ZoneOffset.UTC);
        this.dataUltimoAcesso = Instant.now();
    }

}
