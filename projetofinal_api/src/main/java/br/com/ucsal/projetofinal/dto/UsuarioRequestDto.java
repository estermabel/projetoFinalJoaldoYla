package br.com.ucsal.projetofinal.dto;

import br.com.ucsal.projetofinal.model.Usuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

public class UsuarioRequestDto {

    private String nome;
    private String login;
    private String senha;
    private Integer perfil;
    private Instant dataCriacao;
    private Instant dataUltimoAcesso;

    public UsuarioRequestDto(Usuario usuario) {
        this.nome = usuario.getNome();
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.perfil = usuario.getPerfil();
        this.dataCriacao = usuario.getDataCriacao();
        this.dataUltimoAcesso = usuario.getDataUltimoAcesso();
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
