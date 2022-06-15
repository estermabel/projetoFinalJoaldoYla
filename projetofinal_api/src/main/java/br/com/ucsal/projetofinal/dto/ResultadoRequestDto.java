package br.com.ucsal.projetofinal.dto;

import br.com.ucsal.projetofinal.JavaExecutor;
import br.com.ucsal.projetofinal.model.CasoTeste;
import br.com.ucsal.projetofinal.model.Resposta;
import br.com.ucsal.projetofinal.model.Resultado;
import br.com.ucsal.projetofinal.model.Tarefa;
import br.com.ucsal.projetofinal.repository.CasoTesteRepository;
import br.com.ucsal.projetofinal.repository.RespostaRepository;
import br.com.ucsal.projetofinal.repository.TarefaRepository;
import lombok.NoArgsConstructor;

public class ResultadoRequestDto {

    private String saidaObtida;
    private Boolean resultado;
    private Long respostaId;
    private Long casoTesteId;
    private JavaExecutor javaExecutor = new JavaExecutor();

    public ResultadoRequestDto() {
    }

    public ResultadoRequestDto(String saidaObtida, Boolean resultado, Long respostaId, Long casoTesteId) {
        this.saidaObtida = saidaObtida;
        this.resultado = resultado;
        this.respostaId = respostaId;
        this.casoTesteId = casoTesteId;
    }

    public Resultado toModel(RespostaRepository respostaRepository, CasoTesteRepository casoTesteRepository) {
        Resposta resposta = respostaRepository.findById(respostaId).orElseThrow(() -> new RuntimeException("Id de usuario não encontrado"));
        CasoTeste casoTeste = casoTesteRepository.findById(casoTesteId).orElseThrow(() -> new RuntimeException("Id de usuario não encontrado"));
        javaExecutor.start(resposta.getCodigo());
        resposta.getTarefa().getTestes().stream().map(
            it -> it.getSaida()
        );
        return new Resultado(javaExecutor.getSaida(), javaExecutor.getTeste(), resposta, casoTeste);
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
