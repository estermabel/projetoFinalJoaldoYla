package br.com.ucsal.projetofinal.dto;

import br.com.ucsal.projetofinal.model.Resposta;
import br.com.ucsal.projetofinal.model.Resultado;
import br.com.ucsal.projetofinal.model.Tarefa;
import br.com.ucsal.projetofinal.model.Usuario;
import br.com.ucsal.projetofinal.repository.ResultadoRepository;
import br.com.ucsal.projetofinal.repository.TarefaRepository;
import br.com.ucsal.projetofinal.repository.UsuarioRepository;

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

    public Resposta toModel(UsuarioRepository usuarioRepository, TarefaRepository tarefaRepository, ResultadoRepository resultadoRepository) {
        Resultado resultado = null;
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Id de usuario não encontrado"));
        Tarefa tarefa = tarefaRepository.findById(tarefaId).orElseThrow(() -> new RuntimeException("Id de tarefa não encontrada"));
        if(resultadoId == null){
            resultadoId = 0L;
        }else{
            resultado = resultadoRepository.findById(resultadoId).orElseThrow(() -> new RuntimeException("Id do resultado não encontrada"));
        }
        return new Resposta(codigo, dataEnvio, usuario, tarefa, resultado);
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
