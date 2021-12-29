package br.com.ucsal.projetofinal.repository;

import br.com.ucsal.projetofinal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
