package br.com.samuel.album_generator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    // Pronto.
    // Agora temos métodos como: save(album), findById(id), findAll(), delete(album)
    List<Album> findAllByGenreId(Long genreId);
    // Tudo de graça!
}