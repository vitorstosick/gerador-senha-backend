package br.com.vitor.geradorsenha.auth;

import br.com.vitor.geradorsenha.exception.GeradorException;
import br.com.vitor.geradorsenha.model.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import br.com.vitor.geradorsenha.services.UsuarioService;

@Service
public class AuthService {

    private final JwtService jwtService;

    @Autowired
    private UsuarioService usuarioService;

    public AuthService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String authenticate(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }

    public Usuario getAuthenticatedUser() throws GeradorException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario authenticatedUser = null;

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            Jwt jwt = (Jwt) principal;
            String userId = jwt.getClaim("sub");

            authenticatedUser = (Usuario) usuarioService.buscarUsuarioPorId(userId);
        }

        return authenticatedUser;
    }
}
