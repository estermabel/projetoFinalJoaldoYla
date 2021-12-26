package br.com.ucsal.projetofinal.dto;

import br.com.ucsal.projetofinal.model.Usuario;

import java.time.Instant;

public class UsuarioResponseDto {

    private String nome;
    private String login;
    private String senha;
    private Integer perfil;
    private Instant dataCriacao;
    private Instant dataUltimoAcesso;

    public UsuarioResponseDto(Usuario usuario) {
        this.nome = usuario.getNome();
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.perfil = usuario.getPerfil();
        this.dataCriacao = Instant.now();
        this.dataUltimoAcesso = Instant.now();
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

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public Instant getDataUltimoAcesso() {
        return dataUltimoAcesso;
    }
}
