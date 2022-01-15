package br.com.ucsal.projetofinal.controller;

import br.com.ucsal.projetofinal.dto.TarefaRequestDto;
import br.com.ucsal.projetofinal.dto.TarefaResponseDto;
import br.com.ucsal.projetofinal.model.Tarefa;
import br.com.ucsal.projetofinal.repository.CasoTesteRepository;
import br.com.ucsal.projetofinal.repository.TarefaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarefa")
public class TarefaController {

    private final TarefaRepository tarefaRepository;

    private final CasoTesteRepository casoTesteRepository;

    public TarefaController(TarefaRepository tarefaRepository, CasoTesteRepository casoTesteRepository) {
        this.tarefaRepository = tarefaRepository;
        this.casoTesteRepository = casoTesteRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Tarefa>> listar() {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        return ResponseEntity.ok().body(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        if (tarefa.isPresent()) {
            return ResponseEntity.ok(new TarefaResponseDto(tarefa.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/")
    public ResponseEntity<TarefaResponseDto> inserir(@RequestBody @Valid TarefaRequestDto tarefaRequestDto) {
        Tarefa tarefa = tarefaRequestDto.toModel();
        if (tarefa.getTestes().isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        tarefaRepository.save(tarefa);
        return ResponseEntity.ok().body(new TarefaResponseDto(tarefa));
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        return tarefaRepository.findById(id).map(
                task -> {
                    task.setTitulo(tarefa.getTitulo());
                    task.setDescricao(tarefa.getDescricao());
                    task.setDataEntrega(tarefa.getDataEntrega());
                    task.setTestes(tarefa.getTestes());
                    Tarefa novaTarefa = tarefaRepository.save(task);
                    return ResponseEntity.ok().body(novaTarefa);
                }
        ).orElse(ResponseEntity.notFound().build());
    }
}
