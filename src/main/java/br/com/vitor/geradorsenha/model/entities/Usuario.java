package br.com.vitor.geradorsenha.model.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private String id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "usuario")
    @JsonBackReference(value = "item_usuario")
    private List<Item> listaDeItens;

    @NotBlank(message = "Email é obrigatorio.")
    @Email(message = "Email deve ser válido.")
    @Column(unique = true)
    private String email;

    @NotBlank
    private String senha;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }
}
