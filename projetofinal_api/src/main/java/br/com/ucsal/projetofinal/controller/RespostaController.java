package br.com.ucsal.projetofinal.controller;

import br.com.ucsal.projetofinal.dto.RespostaRequestDto;
import br.com.ucsal.projetofinal.dto.RespostaResponseDto;
import br.com.ucsal.projetofinal.model.CasoTeste;
import br.com.ucsal.projetofinal.model.Resposta;
import br.com.ucsal.projetofinal.model.Resultado;
import br.com.ucsal.projetofinal.model.Teste;
import br.com.ucsal.projetofinal.repository.RespostaRepository;
import br.com.ucsal.projetofinal.repository.ResultadoRepository;
import br.com.ucsal.projetofinal.repository.TarefaRepository;
import br.com.ucsal.projetofinal.repository.UsuarioRepository;
import br.com.ucsal.projetofinal.testcode.TestResult;
import br.com.ucsal.projetofinal.testcode.TestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/respostas")
public class RespostaController {

    private final RespostaRepository respostaRepository;
    private final UsuarioRepository usuarioRepository;
    private final TarefaRepository tarefaRepository;
    private final ResultadoRepository resultadoRepository;

    public RespostaController(RespostaRepository respostaRepository, UsuarioRepository usuarioRepository, TarefaRepository tarefaRepository, ResultadoRepository resultadoRepository) {
        this.respostaRepository = respostaRepository;
        this.usuarioRepository = usuarioRepository;
        this.tarefaRepository = tarefaRepository;
        this.resultadoRepository = resultadoRepository;
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
        List<String> input = new ArrayList<>();
        List<String> output = new ArrayList<>();
        respostaRepository.save(resposta);
        for (CasoTeste teste: resposta.getTarefa().getTestes()) {
            input.add(teste.getEntrada());
            output.add(teste.getSaida());
        }
        Object[] array1 = input.toArray();
        Object[] array2 = output.toArray();

        TestResult tr = new TestService().executetest(resposta.getCodigo(), "Main.java", "", array1, array2);
        List<Teste> ts = new ArrayList<>();
        for(Teste teste: tr.getTest()) {
            ts.add(teste);
        }

        Resultado rs = new Resultado(tr.getOutput(), tr.getCreate(), tr.getCompile(), 22.0, resposta, ts);
        resultadoRepository.save(rs);
        return ResponseEntity.ok().body(new RespostaResponseDto(resposta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resposta> atualizar (@PathVariable Long id, @RequestBody Resposta resposta){
        return respostaRepository.findById(id).map(
                response -> {
                    response.setCodigo(resposta.getCodigo());
                    Resposta novaResposta = respostaRepository.save(response);
                    return ResponseEntity.ok().body(novaResposta);
                }
        ).orElse(ResponseEntity.notFound().build());
    }
}
