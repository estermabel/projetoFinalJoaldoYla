package br.com.ucsal.projetofinal.dto;

import br.com.ucsal.projetofinal.model.CasoTeste;
import br.com.ucsal.projetofinal.model.Tarefa;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TarefaResponseDto {

    private String titulo;
    private String descricao;
    private LocalDateTime dataEntrega;
    private List<CasoTeste> testes = new ArrayList<>();

    public TarefaResponseDto(Tarefa tarefa) {
        this.titulo = tarefa.getTitulo();
        this.descricao = tarefa.getDescricao();
        this.dataEntrega = tarefa.getDataEntrega();
        this.testes = tarefa.getTestes();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public List<CasoTeste> getTestes() {
        return testes;
    }
}
