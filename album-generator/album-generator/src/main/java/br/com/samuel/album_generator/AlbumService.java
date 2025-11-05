package br.com.samuel.album_generator; // Lembre-se do seu underscore!

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service // <-- AVISAMOS AO SPRING QUE ESTA É A CLASSE DE SERVIÇO
public class AlbumService {

    // 1. Os repositórios agora moram aqui, no Service
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AlbumRepository albumRepository;

    // 2. Trazemos a lógica do Controller para cá
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }

    // 3. A LÓGICA DE NEGÓCIO (O SORTEIO) AGORA VIVE AQUI
    public Album getRandomAlbumByGenre(Long genreId) {
        List<Album> albumsOfGenre = albumRepository.findAllByGenreId(genreId);

        if (albumsOfGenre.isEmpty()) {
            // No service, é comum lançar uma exceção
            // Mas por enquanto, vamos apenas retornar null
            return null; 
        }

        Random random = new Random();
        int randomIndex = random.nextInt(albumsOfGenre.size());
        return albumsOfGenre.get(randomIndex);
    }
}