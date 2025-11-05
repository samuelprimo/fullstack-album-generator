package br.com.samuel.album_generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AlbumService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AlbumRepository albumRepository;

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


    public Album getRandomAlbumByGenre(Long genreId) {
        List<Album> albumsOfGenre = albumRepository.findAllByGenreId(genreId);

        if (albumsOfGenre.isEmpty()) {
            return null; 
        }

        Random random = new Random();
        int randomIndex = random.nextInt(albumsOfGenre.size());
        return albumsOfGenre.get(randomIndex);
    }
}