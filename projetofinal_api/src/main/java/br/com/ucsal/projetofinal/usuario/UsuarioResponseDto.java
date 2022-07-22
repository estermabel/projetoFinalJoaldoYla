package br.com.ucsal.projetofinal.usuario;

import br.com.ucsal.projetofinal.perfil.Perfil;
import br.com.ucsal.projetofinal.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.util.List;

public class UsuarioResponseDto {
    private long id;
    private String nome;
    private String login;
    private String senha;
    private Boolean flagAtivo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant dataCriacao;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant dataUltimoAcesso;
    private List<Perfil> perfil;

    public UsuarioResponseDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.flagAtivo = usuario.getFlagAtivo();
        this.dataCriacao = Instant.now();
        this.dataUltimoAcesso = Instant.now();
        this.perfil = usuario.getPerfil();
    }
    public long getId() {
        return id;
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

    public List<Perfil> getPerfil() {
        return perfil;
    }
}
