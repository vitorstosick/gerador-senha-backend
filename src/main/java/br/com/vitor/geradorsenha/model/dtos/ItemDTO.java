package br.com.vitor.geradorsenha.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDTO {

    private String nome;
    private String senha;
}
