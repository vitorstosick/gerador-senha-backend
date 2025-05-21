package br.com.vitor.geradorsenha.controllers;

import br.com.vitor.geradorsenha.auth.AuthService;
import br.com.vitor.geradorsenha.exception.GeradorException;
import br.com.vitor.geradorsenha.model.dtos.ItemDTO;
import br.com.vitor.geradorsenha.model.entities.Usuario;
import br.com.vitor.geradorsenha.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<Void> cadastrarItem(@Valid @RequestBody ItemDTO dto) throws GeradorException {

        Usuario usuario = authService.getAuthenticatedUser();

        itemService.cadastrar(dto);

        return ResponseEntity.created(null).build();
    }

}
