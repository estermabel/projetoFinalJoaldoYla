package br.com.ucsal.projetofinal.tarefa;

import br.com.ucsal.projetofinal.tarefa.TarefaRequestDto;
import br.com.ucsal.projetofinal.tarefa.TarefaResponseDto;
import br.com.ucsal.projetofinal.casoteste.CasoTeste;
import br.com.ucsal.projetofinal.tarefa.Tarefa;
import br.com.ucsal.projetofinal.casoteste.CasoTesteRepository;
import br.com.ucsal.projetofinal.tarefa.TarefaRepository;
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

        try {
            Tarefa tarefa = tarefaRequestDto.toModel();
            for (CasoTeste teste: tarefa.getTestes()) { // zerar o id dos teste q vem como 0 do front
                teste.setId(null);
            }
            if (tarefa.getTestes().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            tarefaRepository.save(tarefa);
            return ResponseEntity.ok().body(new TarefaResponseDto(tarefa));
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
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
