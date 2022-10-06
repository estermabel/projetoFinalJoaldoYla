package br.com.ucsal.projetofinal.prova;

import br.com.ucsal.projetofinal.itemProva.ItemProva;
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
        //List<Tarefa> tarefasEncontradas = new ArrayList<>();
        Prova prova = new Prova();
        prova.setNome(nome);
        prova.setDataEntrega(dataEntrega);

        int count = 1;
        for (Long idTarefa : tarefas) {
            Tarefa tarefa = tarefaRepository.buscarTarefa(idTarefa).orElseThrow(() -> new RuntimeException("Id de tarefa não encontrado"));
            //tarefasEncontradas.add(tarefa);
            prova.adicionarItem(new ItemProva(count++, prova, tarefa));
        }

        return prova;
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
