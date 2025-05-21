package br.com.vitor.geradorsenha.model.repositories;

import br.com.vitor.geradorsenha.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
