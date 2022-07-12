package br.com.ucsal.projetofinal.usuario;

import br.com.ucsal.projetofinal.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
