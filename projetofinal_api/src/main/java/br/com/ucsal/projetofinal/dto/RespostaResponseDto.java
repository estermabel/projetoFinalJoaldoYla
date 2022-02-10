package br.com.ucsal.projetofinal.dto;

import br.com.ucsal.projetofinal.model.Resposta;
import br.com.ucsal.projetofinal.model.Tarefa;
import br.com.ucsal.projetofinal.model.Usuario;

import java.time.LocalDateTime;

public class RespostaResponseDto {

    private String codigo;
    private LocalDateTime dataEnvio;
    private Long usuarioId;
    private Long tarefaId;

    public RespostaResponseDto(Resposta resposta) {
        this.codigo = resposta.getCodigo();
        this.dataEnvio = resposta.getDataEnvio();
        this.usuarioId = resposta.getUsuario().getId();
        this.tarefaId = resposta.getTarefa().getId();
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public Long getUsuario() {
        return usuarioId;
    }

    public Long getTarefa() {
        return tarefaId;
    }
}
