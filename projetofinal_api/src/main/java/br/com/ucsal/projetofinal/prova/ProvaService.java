package br.com.ucsal.projetofinal.prova;

import br.com.ucsal.projetofinal.tarefa.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvaService {

    private final ProvaRepository provaRepository;

    private final TarefaRepository tarefaRepository;

    public ProvaService(ProvaRepository provaRepository, TarefaRepository tarefaRepository) {
        this.provaRepository = provaRepository;
        this.tarefaRepository = tarefaRepository;
    }


    public List<Prova> listar() {
        return provaRepository.findAll();
    }

    public Optional<Prova> listarPorId(Long id) {
        return provaRepository.findById(id);
    }

    public List<Prova> listarPorIdProva(Long id) {
        return provaRepository.listarPorIdProva(id);
    }

    public Prova inserir(ProvaRequestDto provaRequestDto) {
        Prova prova = provaRequestDto.toModel(tarefaRepository);
        return provaRepository.save(prova);
    }
}
