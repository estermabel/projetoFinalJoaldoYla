package br.com.ucsal.projetofinal.tarefa;

import br.com.ucsal.projetofinal.casoteste.CasoTeste;
import br.com.ucsal.projetofinal.usuario.Usuario;
import br.com.ucsal.projetofinal.usuario.UsuarioRepository;

import java.util.List;

public class TarefaRequestDto {

    private String titulo;
    private String descricao;
    private Integer status;
    private List<CasoTeste> testes;
    private Long usuarioId;

    public TarefaRequestDto() {
    }

    public TarefaRequestDto(String titulo, String descricao, Integer status, List<CasoTeste> testes, Long usuarioId) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.testes = testes;
        this.usuarioId = usuarioId;
    }

    public Tarefa toModel(UsuarioRepository usuarioRepository) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Id de usuario não encontrado"));
        return new Tarefa(titulo, descricao, status, testes, usuario);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<CasoTeste> getTestes() {
        return testes;
    }

    public Integer getStatus() {
        return status;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
}
