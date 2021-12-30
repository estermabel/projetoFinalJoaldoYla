package br.com.ucsal.projetofinal.controller;

import br.com.ucsal.projetofinal.dto.RespostaRequestDto;
import br.com.ucsal.projetofinal.dto.RespostaResponseDto;
import br.com.ucsal.projetofinal.model.Resposta;
import br.com.ucsal.projetofinal.model.Tarefa;
import br.com.ucsal.projetofinal.repository.RespostaRepository;
import br.com.ucsal.projetofinal.repository.TarefaRepository;
import br.com.ucsal.projetofinal.repository.UsuarioRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/respostas")
public class RespostaController {

    private final RespostaRepository respostaRepository;
    private final UsuarioRepository usuarioRepository;
    private final TarefaRepository tarefaRepository;

    public RespostaController(RespostaRepository respostaRepository, UsuarioRepository usuarioRepository, TarefaRepository tarefaRepository) {
        this.respostaRepository = respostaRepository;
        this.usuarioRepository = usuarioRepository;
        this.tarefaRepository = tarefaRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Resposta>> listar(){
        List<Resposta> respostas = respostaRepository.findAll();
        return ResponseEntity.ok().body(respostas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id){
        Optional<Resposta> resposta = respostaRepository.findById(id);
        if(resposta.isPresent()){
            return ResponseEntity.ok().body(new RespostaResponseDto(resposta.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/")
    public ResponseEntity<RespostaResponseDto> inserir(@RequestBody @Valid RespostaRequestDto respostaRequestDto){
        Resposta resposta = respostaRequestDto.toModel(usuarioRepository, tarefaRepository);
        respostaRepository.save(resposta);
        return ResponseEntity.ok().body(new RespostaResponseDto(resposta));
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar (@PathVariable Long id, @RequestBody Resposta resposta){
        return respostaRepository.findById(id).map(
                response -> {
                    response.setCodigo(resposta.getCodigo());
                    Resposta novaResposta = respostaRepository.save(response);
                    return ResponseEntity.ok().body(novaResposta);
                }
        ).orElse(ResponseEntity.notFound().build());
    }
}
