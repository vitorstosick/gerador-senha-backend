package br.com.vitor.geradorsenha.model.repositories;

import br.com.vitor.geradorsenha.model.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
}
