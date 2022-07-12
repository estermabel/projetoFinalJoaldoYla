package br.com.ucsal.projetofinal.tarefa;

import br.com.ucsal.projetofinal.casoteste.CasoTeste;
import br.com.ucsal.projetofinal.casoteste.CasoTesteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    private final CasoTesteRepository casoTesteRepository;

    public TarefaService(TarefaRepository tarefaRepository, CasoTesteRepository casoTesteRepository) {
        this.tarefaRepository = tarefaRepository;
        this.casoTesteRepository = casoTesteRepository;
    }

    public List<Tarefa> listar() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> listarPorId(Long id) {
        Optional<Tarefa> tarefa =tarefaRepository.findById(id);
        return tarefa;
    }

    public Tarefa inserir(TarefaRequestDto tarefaRequestDto) throws Exception{
        Tarefa tarefa = tarefaRequestDto.toModel();
        for (CasoTeste teste: tarefa.getTestes()) { // zerar o id dos teste q vem como 0 do front
            teste.setId(null);
        }
        if (tarefa.getTestes().isEmpty()) {
            throw  new Exception("Nenhum caso de teste cadastrado");
        }

        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizar(Long id, Tarefa tarefa){
        return tarefaRepository.findById(id).map(
                task -> {
                    task.setTitulo(tarefa.getTitulo());
                    task.setDescricao(tarefa.getDescricao());
                    task.setDataEntrega(tarefa.getDataEntrega());
                    task.setTestes(tarefa.getTestes());
                    Tarefa novaTarefa = tarefaRepository.save(task);
                    return novaTarefa;
                }
        ).orElse(null);
    }
}
