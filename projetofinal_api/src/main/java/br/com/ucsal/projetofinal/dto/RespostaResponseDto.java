package br.com.ucsal.projetofinal.dto;

import br.com.ucsal.projetofinal.model.Resposta;

import java.time.LocalDateTime;

public class RespostaResponseDto {

    private String codigo;
    private LocalDateTime dataEnvio;
    private Long usuarioId;
    private Long tarefaId;
    private Long resultadoId;

    public RespostaResponseDto(Resposta resposta) {
        this.codigo = resposta.getCodigo();
        this.dataEnvio = resposta.getDataEnvio();
        this.usuarioId = resposta.getUsuario().getId();
        this.tarefaId = resposta.getTarefa().getId();
        this.resultadoId = resposta.getResultado().getId();
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

    public Long getResultado() {
        return resultadoId;
    }
}
