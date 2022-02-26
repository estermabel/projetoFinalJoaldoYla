package br.com.ucsal.projetofinal.dto;

import br.com.ucsal.projetofinal.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class UsuarioRequestDto {

    private String nome;
    private String login;
    private String senha;
    private Integer perfil;
    private Boolean flagAtivo;
    private Instant dataCriacao;
    private Instant dataUltimoAcesso;

    public UsuarioRequestDto() {
    }

    public UsuarioRequestDto(String nome, String login, String senha, Integer perfil, Boolean flagAtivo) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
        this.flagAtivo = flagAtivo;
        this.dataCriacao = Instant.now();
        this.dataUltimoAcesso = Instant.now();
    }

    public Usuario toModel() {
        return new Usuario(nome, login, senha, perfil, flagAtivo);
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Integer getPerfil() {
        return perfil;
    }

    public Boolean getFlagAtivo() {
        return flagAtivo;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public Instant getDataUltimoAcesso() {
        return dataUltimoAcesso;
    }
}
