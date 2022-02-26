package br.com.ucsal.projetofinal.dto;

import br.com.ucsal.projetofinal.model.Resultado;

public class ResultadoResponseDto {

    private String saidaObtida;
    private Boolean resultado;
    private Long respostaId;

    public ResultadoResponseDto(Resultado resultado) {
        this.saidaObtida = resultado.getSaidaObtida();
        this.resultado = resultado.getResultado();
        this.respostaId = resultado.getResposta().getId();
    }

    public String getSaidaObtida() {
        return saidaObtida;
    }

    public Boolean getResultado() {
        return resultado;
    }

    public Long getRespostaId() {
        return respostaId;
    }
}
