package br.com.samuel.album_generator; // Lembre-se do seu underscore!

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AlbumController {

    // 1. O CONTROLLER AGORA SÓ CONHECE O SERVICE!
    // Adeus, repositórios!
    @Autowired
    private AlbumService albumService;

    // --- ENDPOINTS DE GÊNERO ---

    @PostMapping("/genres")
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        // O Controller só repassa o pedido e o resultado
        Genre savedGenre = albumService.createGenre(genre);
        return ResponseEntity.status(201).body(savedGenre);
    }

    @GetMapping("/genres")
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = albumService.getAllGenres();
        return ResponseEntity.ok(genres);
    }

    // --- ENDPOINTS DE ÁLBUM ---
    
    @PostMapping("/albums")
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        Album savedAlbum = albumService.createAlbum(album);
        return ResponseEntity.status(201).body(savedAlbum);
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums = albumService.getAllAlbums();
        return ResponseEntity.ok(albums);
    }

    // --- ENDPOINT DE SORTEIO ---

    @GetMapping("/albums/random")
    public ResponseEntity<Album> getRandomAlbumByGenre(@RequestParam Long genreId) {
        // 1. O Controller só pede o sorteio
        Album randomAlbum = albumService.getRandomAlbumByGenre(genreId);

        // 2. O Controller ainda é responsável pela RESPOSTA HTTP
        if (randomAlbum == null) {
            return ResponseEntity.notFound().build(); // Retorna 404
        } else {
            return ResponseEntity.ok(randomAlbum); // Retorna 200 com o álbum
        }
    }
}