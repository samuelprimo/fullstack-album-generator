package br.com.samuel.album_generator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // <-- Diz ao Spring que esta é uma interface de Repositório
public interface GenreRepository extends JpaRepository<Genre, Long> {
    // É só isso. Sério.
    // O Spring Data JPA vai magicamente criar todos os métodos para nós.
}