package br.com.ucsal.projetofinal.dto;

import br.com.ucsal.projetofinal.model.Tarefa;
import br.com.ucsal.projetofinal.repository.CasoTesteRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TarefaRequestDto {

    private String titulo;
    private String descricao;
    private LocalDateTime dataEntrega;
    private List<CasoTesteRequestDto> testes = new ArrayList<>();

    public TarefaRequestDto(String titulo, String descricao, LocalDateTime dataEntrega, List<CasoTesteRequestDto> testes) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataEntrega = dataEntrega;
        this.testes.addAll(testes);
    }

    public Tarefa toModel(CasoTesteRepository casoTesteRepository) {
        return new Tarefa(titulo, descricao, dataEntrega, testes);
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

    public List<CasoTesteRequestDto> getTestes() {
        return testes;
    }
}
