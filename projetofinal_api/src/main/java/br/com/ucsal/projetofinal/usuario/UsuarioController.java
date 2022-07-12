package br.com.ucsal.projetofinal.usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080/")
@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {
    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(new UsuarioResponseDto(usuario.get()));
        }
        logger.error("Não existe usuario com esse id");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @CrossOrigin
    @PostMapping("/")
    public ResponseEntity<UsuarioResponseDto> inserir(@RequestBody @Valid UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = usuarioRequestDto.toModel();
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().body(new UsuarioResponseDto(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioRepository.findById(id).map(
                user -> {
                    user.setNome(usuario.getNome());
                    user.setLogin(usuario.getLogin());
                    user.setSenha(usuario.getSenha());
                    user.setDataUltimoAcesso(Instant.now());
                    Usuario usuarioAtualizado = usuarioRepository.save(user);
                    return ResponseEntity.ok().body(usuarioAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }
}
