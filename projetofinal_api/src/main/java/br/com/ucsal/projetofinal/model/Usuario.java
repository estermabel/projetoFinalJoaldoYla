package br.com.ucsal.projetofinal.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

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
    private Instant dataCriacao;

    @NotNull
    private Instant dataUltimoAcesso;

    public Usuario(String nome, String login, String senha, Integer perfil, Instant dataCriacao) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
        this.dataCriacao = dataCriacao;
        this.dataUltimoAcesso = Instant.now();
    }

}
