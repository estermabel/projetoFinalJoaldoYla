package br.com.ucsal.projetofinal.dto;

import br.com.ucsal.projetofinal.model.CasoTeste;

public class CasoTesteRequestDto {

    private String nomeTeste;
    private String entrada;
    private String saida;
    private Integer comparacao;

    public CasoTesteRequestDto(String nomeTeste, String entrada, String saida, Integer comparacao) {
        this.nomeTeste = nomeTeste;
        this.entrada = entrada;
        this.saida = saida;
        this.comparacao = comparacao;
    }

    public CasoTeste toModel() {
        return new CasoTeste(nomeTeste, entrada, saida, comparacao);
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
}
