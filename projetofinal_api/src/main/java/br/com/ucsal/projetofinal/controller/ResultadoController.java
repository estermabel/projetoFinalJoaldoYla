package br.com.ucsal.projetofinal.controller;

import br.com.ucsal.projetofinal.dto.ResultadoRequestDto;
import br.com.ucsal.projetofinal.dto.ResultadoResponseDto;
import br.com.ucsal.projetofinal.model.Resultado;
import br.com.ucsal.projetofinal.repository.CasoTesteRepository;
import br.com.ucsal.projetofinal.repository.RespostaRepository;
import br.com.ucsal.projetofinal.repository.ResultadoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resultados")
public class ResultadoController {

    private final RespostaRepository respostaRepository;
    private final ResultadoRepository resultadoRepository;
    private final CasoTesteRepository casoTesteRepository;

    public ResultadoController(RespostaRepository respostaRepository, ResultadoRepository resultadoRepository, CasoTesteRepository casoTesteRepository) {
        this.respostaRepository = respostaRepository;
        this.resultadoRepository = resultadoRepository;
        this.casoTesteRepository = casoTesteRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Resultado>> listar() {
        List<Resultado> resultados = resultadoRepository.findAll();
        return ResponseEntity.ok().body(resultados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
        Optional<Resultado> resultado = resultadoRepository.findById(id);
        if (resultado.isPresent()) {
            return ResponseEntity.ok().body(new ResultadoResponseDto(resultado.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/")
    public ResponseEntity<ResultadoResponseDto> inserir(@RequestBody @Valid ResultadoRequestDto resultadoRequestDto) {
        Resultado resultado = resultadoRequestDto.toModel(respostaRepository, casoTesteRepository);
        resultadoRepository.save(resultado);
        return ResponseEntity.ok().body(new ResultadoResponseDto(resultado));
    }
}