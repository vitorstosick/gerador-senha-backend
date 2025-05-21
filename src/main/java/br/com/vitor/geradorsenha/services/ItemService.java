package br.com.vitor.geradorsenha.services;

import br.com.vitor.geradorsenha.exception.GeradorException;
import br.com.vitor.geradorsenha.model.dtos.ItemDTO;
import br.com.vitor.geradorsenha.model.dtos.ItemSimplesDTO;
import br.com.vitor.geradorsenha.model.entities.Item;
import br.com.vitor.geradorsenha.model.entities.Usuario;
import br.com.vitor.geradorsenha.model.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public HttpStatus cadastrarItem(ItemDTO dto, Usuario usuario) throws GeradorException{

        if(itemRepository.findByNome(dto.getNome()).isPresent()) {
            throw new GeradorException("Nome do item já cadastrado", HttpStatus.CONFLICT);
        }
        Item item = new Item();
        item.setNome(dto.getNome());
        item.setSenha(dto.getSenha());
        item.setUsuario(usuario);
        itemRepository.save(item);

        return HttpStatus.CREATED;
    }

    public void deletarItem(String itemId) throws GeradorException{
        if (!itemRepository.existsById(itemId)) {
            throw new GeradorException("Item nao encontrado",HttpStatus.NOT_FOUND);
        }
        itemRepository.deleteById(itemId);
    }

    public List<ItemSimplesDTO> buscarTodosItens(String idUsuario) {
        return itemRepository.findAllByUsuarioId(idUsuario);
    }
}
