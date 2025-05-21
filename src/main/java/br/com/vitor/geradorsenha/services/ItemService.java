package br.com.vitor.geradorsenha.services;

import br.com.vitor.geradorsenha.model.dtos.ItemDTO;
import br.com.vitor.geradorsenha.model.entities.Item;
import br.com.vitor.geradorsenha.model.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public HttpStatus cadastrar(ItemDTO dto) {

        mapearParaDTO(dto);
        itemRepository.save(mapearParaDTO(dto));
        return HttpStatus.CREATED;
    }

    public Item mapearParaDTO(ItemDTO dto) {
        Item item = new Item();
        item.setNome(dto.getNome());
        item.setSenha(dto.getSenha());
        return item;
    }
}
