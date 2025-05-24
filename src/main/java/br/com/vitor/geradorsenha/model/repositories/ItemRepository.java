package br.com.vitor.geradorsenha.model.repositories;

import br.com.vitor.geradorsenha.model.dtos.ItemSimplesDTO;
import br.com.vitor.geradorsenha.model.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    Optional<Item> findByNomeAndUsuarioId(String nome, String usuarioId);

    @Query("SELECT new br.com.vitor.geradorsenha.model.dtos.ItemSimplesDTO(i.id, i.nome, i.senha) FROM Item i WHERE i.usuario.id = :idUsuario")
    List<ItemSimplesDTO> findAllByUsuarioId(@Param("idUsuario") String idUsuario);

}
