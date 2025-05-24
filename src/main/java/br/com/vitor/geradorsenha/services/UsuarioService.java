package br.com.vitor.geradorsenha.services;

import br.com.vitor.geradorsenha.exception.GeradorException;
import br.com.vitor.geradorsenha.model.dtos.UsuarioDTO;
import br.com.vitor.geradorsenha.model.entities.Usuario;
import br.com.vitor.geradorsenha.model.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails buscarUsuarioPorId(String id) throws GeradorException {

        return usuarioRepository.findById(id).orElseThrow(() -> new GeradorException("Usuario não encontrado", HttpStatus.NOT_FOUND));
    }

    public HttpStatus signUp(UsuarioDTO dto) throws GeradorException {

        validarFormatoEmail(dto.getEmail());
        validarEmailJaExistente(dto);

        if (!dto.getSenha().equals(dto.getConfirmacaoSenha())) {
            throw new GeradorException("As senhas não são iguais", HttpStatus.BAD_REQUEST);
        }
        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());

        dto.setSenha(senhaCriptografada);

        mapearParaDTO(dto);
        usuarioRepository.save(mapearParaDTO(dto));

        return HttpStatus.CREATED;
    }

    private void validarEmailJaExistente(UsuarioDTO dto) throws GeradorException {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(dto.getEmail());

        if (usuarioExistente.isPresent()) {
            throw new GeradorException("Email já cadastrado", HttpStatus.CONFLICT);
        }

    }

    public Usuario buscarPorEmail(String email) throws UsernameNotFoundException {

        return usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));
    }

    public Usuario mapearParaDTO(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setNome(dto.getNome());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setSenha(dto.getSenha());
        return usuario;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.buscarPorEmail(username);
    }

    private void validarFormatoEmail(String email) throws GeradorException {
        String regexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (email == null || !email.matches(regexEmail)) {
            throw new GeradorException("Formato de email inválido", HttpStatus.BAD_REQUEST);
        }
    }

}
