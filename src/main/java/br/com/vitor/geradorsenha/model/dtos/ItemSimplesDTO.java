package br.com.vitor.geradorsenha.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemSimplesDTO {

    private String id;
    private String nome;
    private String senha;
}
