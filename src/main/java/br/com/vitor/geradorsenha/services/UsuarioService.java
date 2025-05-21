package br.com.vitor.geradorsenha.services;

import br.com.vitor.geradorsenha.exception.GeradorException;
import br.com.vitor.geradorsenha.model.dtos.UsuarioDTO;
import br.com.vitor.geradorsenha.model.entities.Usuario;
import br.com.vitor.geradorsenha.model.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UserDetails buscarUsuarioPorId(String id) throws GeradorException {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new GeradorException("Usuario não encontrado", HttpStatus.NOT_FOUND));

        return usuario;
    }

    public HttpStatus signUp(UsuarioDTO dto) {
        mapearParaDTO(dto);
        usuarioRepository.save(mapearParaDTO(dto));
        return HttpStatus.CREATED;
    }

    public Usuario mapearParaDTO(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setNome(dto.getNome());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setSenha(dto.getSenha());
        return usuario;
    }
}
