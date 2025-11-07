package br.com.samuel.album_generator; // Lembre-se do seu underscore!

// Importa as ferramentas que vamos usar
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component // <-- Diz ao Spring: "Ei, esta classe é um componente, gerencie ela!"
public class DataSeeder implements CommandLineRunner { // <-- Interface que "roda" no início

    // 1. Injeta os repositórios, pois vamos usá-los para salvar dados
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AlbumRepository albumRepository;

    // 2. Este é o método que o Spring vai executar automaticamente
    @Override
    public void run(String... args) throws Exception {
        
        // Vamos checar se o banco já tem dados, para não duplicar
        // (Isso é mais útil para o Degrau 2, mas é uma boa prática)
        if (genreRepository.count() == 0 && albumRepository.count() == 0) {
            System.out.println(">>> BANCO VAZIO. CADASTRANDO DADOS DE EXEMPLO (SEEDING)... <<<");
            
            // --- ETAPA 1: Criar e Salvar os Gêneros ---
            // Precisamos salvar e pegar o objeto de volta, pois ele agora tem um ID
            
            Genre rock = new Genre();
            rock.setName("Rock");
            Genre rockSalvo = genreRepository.save(rock);

            Genre samba = new Genre();
            samba.setName("Samba");
            Genre sambaSalvo = genreRepository.save(samba);

            Genre eletronica = new Genre();
            eletronica.setName("Eletrônica");
            Genre eletronicaSalva = genreRepository.save(eletronica);

            // --- ETAPA 2: Criar os Álbuns e ligar aos Gêneros salvos ---
            
            // Álbuns de Rock
            Album album1 = new Album();
            album1.setTitle("The Dark Side of the Moon");
            album1.setArtist("Pink Floyd");
            album1.setCoverUrl("https://upload.wikimedia.org/wikipedia/pt/3/3b/Dark_Side_of_the_Moon.png");
            album1.setGenre(rockSalvo); // <-- A MÁGICA: ligamos ao gênero salvo

            Album album2 = new Album();
            album2.setTitle("Led Zeppelin IV");
            album2.setArtist("Led Zeppelin");
            album2.setCoverUrl("https://upload.wikimedia.org/wikipedia/en/2/26/Led_Zeppelin_-_Led_Zeppelin_IV.jpg");
            album2.setGenre(rockSalvo);

            // Álbuns de Samba
            Album album3 = new Album();
            album3.setTitle("Cartola (1976)");
            album3.setArtist("Cartola");
            album3.setCoverUrl("https://upload.wikimedia.org/wikipedia/pt/6/6d/Cartola_-_1976.jpg");
            album3.setGenre(sambaSalvo);

            // Álbuns de Eletrônica
            Album album4 = new Album();
            album4.setTitle("Discovery");
            album4.setArtist("Daft Punk");
            album4.setCoverUrl("https://upload.wikimedia.org/wikipedia/en/a/ae/Daft_Punk_-_Discovery.jpg");
            album4.setGenre(eletronicaSalva);

            Album album5 = new Album();
            album5.setTitle("Random Access Memories");
            album5.setArtist("Daft Punk");
            album5.setCoverUrl("https://upload.wikimedia.org/wikipedia/en/a/a7/Random_Access_Memories.jpg");
            album5.setGenre(eletronicaSalva);

            // 3. Salva todos os álbuns no banco de uma vez
            albumRepository.saveAll(List.of(album1, album2, album3, album4, album5));
            
            System.out.println(">>> DADOS CADASTRADOS COM SUCESSO. <<<");
        } else {
            System.out.println(">>> O banco já contém dados. O Seeder não será executado. <<<");
        }
    }
}