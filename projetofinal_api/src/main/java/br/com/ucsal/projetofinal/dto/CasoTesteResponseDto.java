package br.com.ucsal.projetofinal.dto;

import br.com.ucsal.projetofinal.model.CasoTeste;
import br.com.ucsal.projetofinal.model.Tarefa;

public class CasoTesteResponseDto {

    private String nomeTeste;
    private String entrada;
    private String saida;
    private Integer comparacao;
    private Long tarefaId;

    public CasoTesteResponseDto(CasoTeste casoTeste) {
        this.nomeTeste = casoTeste.getNomeTeste();
        this.entrada = casoTeste.getEntrada();
        this.saida = casoTeste.getSaida();
        this.comparacao = casoTeste.getComparacao();
        this.tarefaId = casoTeste.getTarefa().getId();
    }

    public String getNomeTeste() {
        return nomeTeste;
    }

    public String getEntrada() {
        return entrada;
    }

    public String getSaida() {
        return saida;
    }

    public Integer getComparacao() {
        return comparacao;
    }

    public Long getTarefaId() {
        return tarefaId;
    }
}
