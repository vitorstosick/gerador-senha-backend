package br.com.vitor.geradorsenha.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private String id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank
    private LocalDate dataNascimento;

//    private List<Item> listaDeItens;

    @NotBlank(message = "Email é obrigatorio.")
    @Email(message = "Email deve ser válido.")
    @Column(unique = true)
    private String email;

    @NotBlank
    private String senha;

    public Usuario() {}

    public Usuario(String id, String nome, LocalDate dataNascimento, List<Item> listaDeItens, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
//        this.listaDeItens = listaDeItens;
        this.email = email;
        this.senha = senha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

//    public List<Item> getListaDeItens() {
//        return listaDeItens;
//    }
//
//    public void setListaDeItens(List<Item> listaDeItens) {
//        this.listaDeItens = listaDeItens;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

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
