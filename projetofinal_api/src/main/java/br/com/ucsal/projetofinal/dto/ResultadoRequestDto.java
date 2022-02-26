package br.com.ucsal.projetofinal.dto;

import br.com.ucsal.projetofinal.model.Resposta;
import br.com.ucsal.projetofinal.model.Resultado;
import br.com.ucsal.projetofinal.repository.RespostaRepository;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

public class ResultadoRequestDto {

    private String saidaObtida;
    private Boolean resultado;
    private Long respostaId;

    public ResultadoRequestDto(String saidaObtida, Boolean resultado, Long respostaId) {
        this.saidaObtida = saidaObtida;
        this.resultado = resultado;
        this.respostaId = respostaId;
    }

    public Resultado toModel(RespostaRepository respostaRepository) {
        Resposta resposta = respostaRepository.findById(respostaId).orElseThrow(() -> new RuntimeException("Id de usuario não encontrado"));
        return new Resultado(saidaObtida, resultado, resposta);
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
