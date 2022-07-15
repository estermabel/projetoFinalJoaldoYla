package br.com.ucsal.projetofinal.usuario;

import br.com.ucsal.projetofinal.usuario.Usuario;

import java.time.Instant;

public class UsuarioRequestDto {

    private String nome;
    private String login;
    private String senha;
    private Boolean flagAtivo;
    private Instant dataCriacao;
    private Instant dataUltimoAcesso;

    public UsuarioRequestDto() {
    }

    public UsuarioRequestDto(String nome, String login, String senha, Integer perfil, Boolean flagAtivo) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.flagAtivo = flagAtivo;
        this.dataCriacao = Instant.now();
        this.dataUltimoAcesso = Instant.now();
    }

    public Usuario toModel() {
        return new Usuario(nome, login, senha, flagAtivo);
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