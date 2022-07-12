package br.com.ucsal.projetofinal.resposta;

import br.com.ucsal.projetofinal.tarefa.Tarefa;
import br.com.ucsal.projetofinal.usuario.Usuario;
import br.com.ucsal.projetofinal.tarefa.TarefaRepository;
import br.com.ucsal.projetofinal.usuario.UsuarioRepository;

import java.time.LocalDateTime;

public class RespostaRequestDto {

    private String codigo;
    private LocalDateTime dataEnvio;
    private Long usuarioId;
    private Long tarefaId;
    private Long resultadoId;

    public RespostaRequestDto() {
    }

    public RespostaRequestDto(String codigo, Long usuarioId, Long tarefaId, Long resultadoId) {
        this.codigo = codigo;
        this.dataEnvio = LocalDateTime.now();
        this.usuarioId = usuarioId;
        this.tarefaId = tarefaId;
        this.resultadoId = resultadoId;
    }

    public Resposta toModel(UsuarioRepository usuarioRepository, TarefaRepository tarefaRepository) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Id de usuario não encontrado"));
        Tarefa tarefa = tarefaRepository.findById(tarefaId).orElseThrow(() -> new RuntimeException("Id de tarefa não encontrada"));
        return new Resposta(codigo, dataEnvio, usuario, tarefa);
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getTarefaId() {
        return tarefaId;
    }

    public Long getResultadoId() {
        return resultadoId;
    }
}
