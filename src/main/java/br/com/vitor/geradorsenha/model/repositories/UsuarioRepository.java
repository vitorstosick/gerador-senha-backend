package br.com.vitor.geradorsenha.model.repositories;

import br.com.vitor.geradorsenha.model.entities.Item;
import br.com.vitor.geradorsenha.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>, JpaSpecificationExecutor<Usuario> {

    Optional<Usuario> findByEmail(String email);
}
