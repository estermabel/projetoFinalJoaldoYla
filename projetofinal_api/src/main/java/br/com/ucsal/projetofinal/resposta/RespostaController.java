package br.com.ucsal.projetofinal.resposta;

import br.com.ucsal.projetofinal.casoteste.CasoTeste;
import br.com.ucsal.projetofinal.resultado.Resultado;
import br.com.ucsal.projetofinal.teste.Teste;
import br.com.ucsal.projetofinal.testcode.TestResult;
import br.com.ucsal.projetofinal.testcode.TestService;
import br.com.ucsal.projetofinal.usuario.UsuarioResponseDto;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/respostas")
public class RespostaController {

    private final RespostaService respostaService;

    public RespostaController(RespostaService respostaService) {
        this.respostaService = respostaService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Resposta>> listar() {
        List<Resposta> respostas = respostaService.listar();
        return ResponseEntity.ok().body(respostas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
        Optional<Resposta> resposta = respostaService.listarPorId(id);
        if (resposta.isPresent()) {
            return ResponseEntity.ok().body(new RespostaResponseDto(resposta.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/")
    public ResponseEntity<RespostaResponseDto> inserir(@RequestBody @Valid RespostaRequestDto respostaRequestDto) {
        Resposta resposta = respostaService.inserir(respostaRequestDto);
        respostaService.gerarResultado(resposta);
        return ResponseEntity.ok().body(new RespostaResponseDto(resposta));
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody Resposta resposta) {
        try {
            return ResponseEntity.ok().body(new RespostaResponseDto(respostaService.atualizar(id, resposta)));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }


}
