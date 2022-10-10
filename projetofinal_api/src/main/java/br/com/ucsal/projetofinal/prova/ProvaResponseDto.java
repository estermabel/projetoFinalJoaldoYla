package br.com.ucsal.projetofinal.prova;

import br.com.ucsal.projetofinal.itemProva.ItemProva;
import br.com.ucsal.projetofinal.tarefa.Tarefa;

import java.time.LocalDateTime;
import java.util.List;

public class ProvaResponseDto {

    private String nome;
    private List<ItemProva> tarefas;
    private LocalDateTime dataEntrega;

    public ProvaResponseDto(Prova prova) {
        this.nome = prova.getNome();
        this.tarefas = prova.getItens();
        this.dataEntrega = prova.getDataEntrega();
    }

    public String getNome() {
        return nome;
    }

    public List<ItemProva> getItens() {
        return tarefas;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }
}
