package br.com.samuel.album_generator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data; // Importa a mágica do Lombok

@Data   // <-- Mágica do Lombok: cria getters, setters, construtores, etc.
@Entity // <-- Diz ao JPA: "Esta classe é uma tabela no banco de dados"
public class Genre {

    @Id // <-- Diz que este é o campo de "ID" (chave primária)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <-- Diz ao banco para gerar o ID automaticamente (1, 2, 3...)
    private Long id;

    private String name; // Ex: "Rock", "Samba", "Eletrônica"

}
