package br.com.ucsal.projetofinal.resposta;

import br.com.ucsal.projetofinal.casoteste.CasoTeste;
import br.com.ucsal.projetofinal.resultado.Resultado;
import br.com.ucsal.projetofinal.resultado.ResultadoRepository;
import br.com.ucsal.projetofinal.tarefa.TarefaRepository;
import br.com.ucsal.projetofinal.testcode.TestResult;
import br.com.ucsal.projetofinal.testcode.TestService;
import br.com.ucsal.projetofinal.teste.Teste;
import br.com.ucsal.projetofinal.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RespostaService {

    private final RespostaRepository respostaRepository;
    private final UsuarioRepository usuarioRepository;
    private final TarefaRepository tarefaRepository;
    private final ResultadoRepository resultadoRepository;

    public RespostaService(RespostaRepository respostaRepository, UsuarioRepository usuarioRepository, TarefaRepository tarefaRepository, ResultadoRepository resultadoRepository) {
        this.respostaRepository = respostaRepository;
        this.usuarioRepository = usuarioRepository;
        this.tarefaRepository = tarefaRepository;
        this.resultadoRepository = resultadoRepository;
    }

    public List<Resposta> listar() {
        return respostaRepository.findAll();
    }

    public Optional<Resposta> listarPorId(Long id) {
        Optional<Resposta> resposta = respostaRepository.findById(id);
        return resposta;
    }

    public List<Resposta> listarPorIdUsuario(Long id) {
        return respostaRepository.findByUsuarioId(id);
    }

    public List<Resposta> listarPorIdTarefa(Long id) {
        return respostaRepository.findByTarefaId(id);
    }

    public Resposta inserir(RespostaRequestDto respostaRequestDto) {
        Resposta resposta = respostaRequestDto.toModel(usuarioRepository, tarefaRepository);
        return respostaRepository.save(resposta);
    }

    public Resposta atualizar(Long id, Resposta resposta) {
        return respostaRepository.findById(id).map(
                response -> {
                    response.setCodigo(resposta.getCodigo());
                    Resposta novaResposta = respostaRepository.save(response);
                    return novaResposta;
                }
        ).orElse(null);
    }

    public Resultado gerarResultado(Resposta resposta) {
        List<String> input = new ArrayList<>();
        List<String> output = new ArrayList<>();


        for (CasoTeste teste : resposta.getTarefa().getTestes()) {
            input.add(teste.getEntrada());
            output.add(teste.getSaida());
        }

        TestResult testResult = new TestService().executetest(resposta.getCodigo(), "Main.java", "", input.toArray(), output.toArray());

        List<Teste> testes = new ArrayList<>();
        Double percentagem = 0.0;

        if (testResult.getCompile()) {
            testes = new ArrayList<>(testResult.getTest());
            percentagem = obterPorcentagem(testes);
        }

        Resultado resultado = new Resultado(testResult.getOutput(), testResult.getCreate(), testResult.getCompile(), percentagem, resposta, testes);
        atribuirPorcentagemResposta(resultado, percentagem);

        return resultadoRepository.save(resultado);
    }

    private void atribuirPorcentagemResposta(Resultado resultado, Double porcentagemAcertoFinal) {
        Optional<Resposta> respostaOptional = respostaRepository.findById(resultado.getResposta().getId()).map(
                respostaEditada -> {
                    respostaEditada.setPorcentagemAcerto(porcentagemAcertoFinal);
                    respostaRepository.save(respostaEditada);
                    return respostaEditada;
                }
        );
    }

    private double obterPorcentagem(List<Teste> testes) {
        int testesCorretos = 0;

        for (Teste teste : testes) {
            if (teste.getResultadoFinal() == true) {
                testesCorretos++;
            }
        }
        double percent = ((testesCorretos * 100) / testes.size());
        return percent;
    }
}
