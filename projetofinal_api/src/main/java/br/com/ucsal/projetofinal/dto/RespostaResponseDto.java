package br.com.ucsal.projetofinal.dto;

import br.com.ucsal.projetofinal.model.Resposta;
import br.com.ucsal.projetofinal.model.Tarefa;
import br.com.ucsal.projetofinal.model.Usuario;

import java.time.LocalDateTime;

public class RespostaResponseDto {

    private String codigo;
    private LocalDateTime dataEnvio;
    private Usuario usuario;
    private Tarefa tarefa;

    public RespostaResponseDto(Resposta resposta) {
        this.codigo = resposta.getCodigo();
        this.dataEnvio = resposta.getDataEnvio();
        this.usuario = resposta.getUsuario();
        this.tarefa = resposta.getTarefa();
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }
}
