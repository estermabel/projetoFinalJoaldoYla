package br.com.ucsal.projetofinal.prova;

import br.com.ucsal.projetofinal.tarefa.Tarefa;

import java.time.LocalDateTime;
import java.util.List;

public class ProvaResponseDto {

    private String nome;
    private List<Tarefa> tarefas;
    private LocalDateTime dataEntrega;

    public ProvaResponseDto(Prova prova) {
        this.nome = prova.getNome();
        this.tarefas = prova.getTarefas();
        this.dataEntrega = prova.getDataEntrega();
    }

    public String getNome() {
        return nome;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }
}
