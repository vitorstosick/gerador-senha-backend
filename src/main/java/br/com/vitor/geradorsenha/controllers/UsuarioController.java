package br.com.vitor.geradorsenha.controllers;

import br.com.vitor.geradorsenha.exception.GeradorException;
import jakarta.validation.Valid;
import br.com.vitor.geradorsenha.model.dtos.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.vitor.geradorsenha.services.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping(path = "/public/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(path = "/signup")
    public ResponseEntity<Void> signUp(@Valid @RequestBody UsuarioDTO dto) throws GeradorException {
        if (!dto.getSenha().equals(dto.getConfirmacaoSenha())) {
            throw new GeradorException("As senhas não são iguais", HttpStatus.BAD_REQUEST);
        }
        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());

        dto.setSenha(senhaCriptografada);

        usuarioService.signUp(dto);

        return ResponseEntity.created(null).build();
    }
}
