package br.com.ucsal.projetofinal.resposta;

import java.time.LocalDateTime;

public class RespostaPorcentagemResponseDTO {

    private Long id;
    private String codigo;
    private LocalDateTime dataEnvio;
    private Long usuarioId;
    private Long tarefaId;
    private Double porcentagemAcerto;

    public RespostaPorcentagemResponseDTO(Resposta resposta) {
        this.id = resposta.getId();
        this.codigo = resposta.getCodigo();
        this.dataEnvio = resposta.getDataEnvio();
        this.usuarioId = resposta.getUsuario().getId();
        this.tarefaId = resposta.getTarefa().getId();
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

    public void setPorcentagemAcerto(Double porcentagemAcerto) {
        this.porcentagemAcerto = porcentagemAcerto;
    }
}
