package br.com.ucsal.projetofinal.controller;

import br.com.ucsal.projetofinal.dto.CasoTesteRequestDto;
import br.com.ucsal.projetofinal.dto.CasoTesteResponseDto;
import br.com.ucsal.projetofinal.model.CasoTeste;
import br.com.ucsal.projetofinal.repository.CasoTesteRepository;
import br.com.ucsal.projetofinal.repository.TarefaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/casoteste")
public class CasoTesteController {

    private final CasoTesteRepository casoTesteRepository;

    private final TarefaRepository tarefaRepository;

    public CasoTesteController(CasoTesteRepository casoTesteRepository,TarefaRepository tarefaRepository) {
        this.casoTesteRepository = casoTesteRepository;
        this.tarefaRepository = tarefaRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<CasoTeste>> listar() {
        List<CasoTeste> casoTestes = casoTesteRepository.findAll();
        return ResponseEntity.ok().body(casoTestes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
        Optional<CasoTeste> casoTeste = casoTesteRepository.findById(id);
        if (casoTeste.isPresent()) {
            return ResponseEntity.ok(casoTeste);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/")
    public ResponseEntity<CasoTesteResponseDto> inserir(@RequestBody @Valid CasoTesteRequestDto casoTesteRequestDto) {
        CasoTeste casoTeste = casoTesteRequestDto.toModel(tarefaRepository);
        casoTesteRepository.save(casoTeste);
        return ResponseEntity.ok().body(new CasoTesteResponseDto(casoTeste));
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody CasoTeste casoTeste) {
        return casoTesteRepository.findById(id).map(
                caso -> {
                    caso.setNomeTeste(casoTeste.getNomeTeste());
                    caso.setEntrada(casoTeste.getEntrada());
                    caso.setSaida(casoTeste.getSaida());
                    caso.setComparacao(casoTeste.getComparacao());
                    CasoTeste casoTesteAtualizado = casoTesteRepository.save(caso);
                    return ResponseEntity.ok().body(casoTesteAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }
}
