package br.com.ucsal.projetofinal.dto;

import br.com.ucsal.projetofinal.model.CasoTeste;
import br.com.ucsal.projetofinal.model.Tarefa;
import br.com.ucsal.projetofinal.repository.CasoTesteRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TarefaRequestDto {

    private String titulo;
    private String descricao;
    private Integer status;
    private LocalDateTime dataEntrega;
    private List<CasoTeste> testes;

    public TarefaRequestDto() {
    }

    public TarefaRequestDto(String titulo, String descricao, Integer status, LocalDateTime dataEntrega, List<CasoTeste> testes) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.dataEntrega = dataEntrega;
        this.testes = testes;
    }

    public Tarefa toModel() {
        return new Tarefa(titulo, descricao, status, dataEntrega, testes);
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

    public Integer getStatus() {
        return status;
    }
}
