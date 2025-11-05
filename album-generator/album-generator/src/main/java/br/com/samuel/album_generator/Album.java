package br.com.samuel.album_generator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;    // Ex: "The Dark Side of the Moon"
    private String artist;   // Ex: "Pink Floyd"
    private String coverUrl; // Ex: "https://minhacapa.com/imagem.png"

    // Esta é a parte importante:
    @ManyToOne // <-- Diz ao JPA: "MUITOS Álbuns podem pertencer a UM Gênero"
    private Genre genre; // Isso vai criar a relação entre as tabelas

}