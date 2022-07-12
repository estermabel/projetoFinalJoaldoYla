package br.com.ucsal.projetofinal.tarefa;

import br.com.ucsal.projetofinal.casoteste.CasoTeste;
import br.com.ucsal.projetofinal.tarefa.Tarefa;

import java.time.LocalDateTime;
import java.util.List;

public class TarefaResponseDto {

    private long id;
    private String titulo;
    private String descricao;
    private Integer status;
    private LocalDateTime dataEntrega;
    private List<CasoTeste> testes;

    public TarefaResponseDto(Tarefa tarefa) {
        this.id = tarefa.getId();
        this.titulo = tarefa.getTitulo();
        this.descricao = tarefa.getDescricao();
        this.status = tarefa.getStatus();
        this.dataEntrega = tarefa.getDataEntrega();
        this.testes = tarefa.getTestes();
    }
    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getStatus() {
        return status;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public List<CasoTeste> getTestes() {
        return testes;
    }
}
