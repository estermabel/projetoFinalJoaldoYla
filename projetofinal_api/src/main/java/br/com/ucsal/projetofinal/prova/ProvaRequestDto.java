package br.com.ucsal.projetofinal.prova;

import br.com.ucsal.projetofinal.tarefa.Tarefa;
import br.com.ucsal.projetofinal.tarefa.TarefaRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProvaRequestDto {
    private String nome;
    private List<Long> tarefas;
    private LocalDateTime dataEntrega;

    public ProvaRequestDto() {
    }

    public ProvaRequestDto(String nome, List<Long> tarefas, LocalDateTime dataEntrega) {
        this.nome = nome;
        this.tarefas = tarefas;
        this.dataEntrega = dataEntrega;
    }

    public Prova toModel(TarefaRepository tarefaRepository) {
        List<Tarefa> tarefasEncontradas = new ArrayList<>();
        for (Long idTarefa : tarefas) {
            Tarefa tarefa = tarefaRepository.findById(idTarefa).orElseThrow(() -> new RuntimeException("Id de tarefa não encontrado"));
            tarefasEncontradas.add(tarefa);
        }
        return new Prova(nome, tarefasEncontradas, dataEntrega);
    }

    public String getNome() {
        return nome;
    }

    public List<Long> getTarefas() {
        return tarefas;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }
}
