package br.com.vitor.geradorsenha.model.dtos;

import jakarta.validation.constraints.Email;

import java.time.LocalDate;

public class UsuarioDTO {

    private String nome;
    @Email
    private String email;
    private LocalDate dataNascimento;
    private String senha;
    private String confirmacaoSenha;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nome, String email, LocalDate dataNascimento, String senha, String confirmacaoSenha) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }
}
