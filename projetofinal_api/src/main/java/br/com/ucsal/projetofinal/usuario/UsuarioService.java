package br.com.ucsal.projetofinal.usuario;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listar(){
        return  usuarioRepository.findAll();
    }

    public Optional<Usuario> listarPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario;
    }

    public Usuario inserir(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = usuarioRequestDto.toModel();
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Long id, Usuario usuario) {
        return usuarioRepository.findById(id).map(
                user -> {
                    user.setNome(usuario.getNome());
                    user.setLogin(usuario.getLogin());
                    user.setSenha(usuario.getSenha());
                    user.setDataUltimoAcesso(Instant.now());
                    Usuario usuarioAtualizado = usuarioRepository.save(user);
                    return usuarioAtualizado;
                }).orElse(null);
    }

    public Optional<Usuario> listarPorLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }
}
