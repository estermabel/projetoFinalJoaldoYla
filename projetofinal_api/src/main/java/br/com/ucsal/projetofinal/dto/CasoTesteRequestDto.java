package br.com.ucsal.projetofinal.dto;

import br.com.ucsal.projetofinal.model.CasoTeste;
import br.com.ucsal.projetofinal.model.Tarefa;
import br.com.ucsal.projetofinal.repository.TarefaRepository;

public class CasoTesteRequestDto {

    private String nomeTeste;
    private String entrada;
    private String saida;
    private Integer comparacao;
    private Long tarefaId;

    public CasoTesteRequestDto() {
    }

    public CasoTesteRequestDto(String nomeTeste, String entrada, String saida, Integer comparacao, Long tarefaId) {
        this.nomeTeste = nomeTeste;
        this.entrada = entrada;
        this.saida = saida;
        this.comparacao = comparacao;
        this.tarefaId = tarefaId;
    }

    public CasoTeste toModel(TarefaRepository tarefaRepository) {
        Tarefa tarefa = tarefaRepository.findById(tarefaId).orElseThrow();

        return new CasoTeste(nomeTeste, entrada, saida, comparacao, tarefa);
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
