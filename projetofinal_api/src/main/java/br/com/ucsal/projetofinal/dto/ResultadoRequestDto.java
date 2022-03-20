package br.com.ucsal.projetofinal.dto;

import br.com.ucsal.projetofinal.model.CasoTeste;
import br.com.ucsal.projetofinal.model.Resposta;
import br.com.ucsal.projetofinal.model.Resultado;
import br.com.ucsal.projetofinal.repository.CasoTesteRepository;
import br.com.ucsal.projetofinal.repository.RespostaRepository;

public class ResultadoRequestDto {

    private String saidaObtida;
    private Boolean resultado;
    private Long respostaId;
    private Long casoTesteId;

    public ResultadoRequestDto(String saidaObtida, Boolean resultado, Long respostaId, Long casoTesteId) {
        this.saidaObtida = saidaObtida;
        this.resultado = resultado;
        this.respostaId = respostaId;
        this.casoTesteId = casoTesteId;
    }

    public Resultado toModel(RespostaRepository respostaRepository, CasoTesteRepository casoTesteRepository) {
        Resposta resposta = respostaRepository.findById(respostaId).orElseThrow(() -> new RuntimeException("Id de usuario não encontrado"));
        CasoTeste casoTeste = casoTesteRepository.findById(casoTesteId).orElseThrow(() -> new RuntimeException("Id de usuario não encontrado"));
        return new Resultado(saidaObtida, resultado, resposta, casoTeste);
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

    public Long getCasoTesteId() {
        return casoTesteId;
    }
}
