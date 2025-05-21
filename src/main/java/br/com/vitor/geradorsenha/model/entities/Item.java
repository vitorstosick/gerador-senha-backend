package br.com.vitor.geradorsenha.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private String id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private String senha;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonManagedReference(value = "item_usuario")
    private Usuario usuario;
}
