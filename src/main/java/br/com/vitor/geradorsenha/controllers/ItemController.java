package br.com.vitor.geradorsenha.controllers;

import br.com.vitor.geradorsenha.auth.AuthService;
import br.com.vitor.geradorsenha.exception.GeradorException;
import br.com.vitor.geradorsenha.model.dtos.ItemDTO;
import br.com.vitor.geradorsenha.model.dtos.ItemSimplesDTO;
import br.com.vitor.geradorsenha.model.entities.Item;
import br.com.vitor.geradorsenha.model.entities.Usuario;
import br.com.vitor.geradorsenha.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<Void> cadastrarItem(@Valid @RequestBody ItemDTO dto) throws GeradorException {

        Usuario usuario = authService.getAuthenticatedUser();

        itemService.cadastrarItem(dto, usuario);

        return ResponseEntity.created(null).build();
    }

    @DeleteMapping(path = "/{idItem}")
    public void deletarItem(@PathVariable String idItem) throws GeradorException {
        Usuario usuario = authService.getAuthenticatedUser();

        itemService.deletarItem(idItem);
    }

    @GetMapping("/items")
    public List<ItemSimplesDTO> buscarTodosItens() throws GeradorException {
        Usuario usuario = authService.getAuthenticatedUser();
        return itemService.buscarTodosItens(usuario.getId());
    }
}
