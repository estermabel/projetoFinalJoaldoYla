package br.com.ucsal.projetofinal.resposta;

import java.time.LocalDateTime;

public class RespostaResponseDto {

    private Long id;
    private String codigo;
    private LocalDateTime dataEnvio;
    private Long usuarioId;
    private Long tarefaId;
    private Double porcentagemAcerto;

    public RespostaResponseDto(Resposta resposta) {
        this.id = resposta.getId();
        this.codigo = resposta.getCodigo();
        this.dataEnvio = resposta.getDataEnvio();
        this.usuarioId = resposta.getUsuario().getId();
        this.tarefaId = resposta.getTarefa().getId();
        this.porcentagemAcerto = resposta.getPorcentagemAcerto();
    }

    public Long getId() {
        return id;
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

    public Double getPorcentagemAcerto() {
        return porcentagemAcerto;
    }
}
