import React, { useState, useEffect } from 'react';

// Vamos adicionar um CSS básico para não ficar tão feio
import './App.css'; // Vamos criar este arquivo

function App() {
  // Estados que já tínhamos:
  const [genres, setGenres] = useState([]); // Mudei o nome de 'data' para 'genres'
  const [loadingGenres, setLoadingGenres] = useState(true);

  // 1. NOVOS ESTADOS para o álbum sorteado
  const [selectedAlbum, setSelectedAlbum] = useState(null); // Vai guardar o objeto do álbum
  const [loadingAlbum, setLoadingAlbum] = useState(false); // Aviso de "sorteando..."

  // --- BUSCA INICIAL DE GÊNEROS (Quase igual a antes) ---
  useEffect(() => {
    const fetchGenres = async () => {
      try {
        setLoadingGenres(true);
        const response = await fetch('http://localhost:8080/api/genres');
        const data = await response.json();
        setGenres(data);
      } catch (error) {
        console.error("Erro ao buscar gêneros:", error);
      } finally {
        setLoadingGenres(false);
      }
    };

    fetchGenres();
  }, []);

  // 2. NOVA FUNÇÃO: Chamada quando o usuário clica em um gênero
  const handleGenreClick = async (genreId) => {
    try {
      setLoadingAlbum(true);    // Mostra "Sorteando..."
      setSelectedAlbum(null); // Limpa o álbum anterior

      // 3. A CHAMADA DA API DE SORTEIO!
      const response = await fetch(`http://localhost:8080/api/albums/random?genreId=${genreId}`);
      
      if (response.status === 404) {
        // 404 Not Found (sem álbuns para esse gênero)
        alert("Ops! Não temos álbuns cadastrados para este gênero.");
        setSelectedAlbum(null);
      } else if (response.ok) {
        const album = await response.json();
        setSelectedAlbum(album); // Guarda o álbum sorteado no estado
      } else {
        throw new Error("Falha na requisição");
      }

    } catch (error) {
      console.error("Erro ao sortear álbum:", error);
      alert("Ocorreu um erro ao sortear.");
    } finally {
      setLoadingAlbum(false); // Esconde "Sorteando..."
    }
  };

  // --- RENDERIZAÇÃO (O que o usuário vê) ---
  
  // 4. Se estiver carregando os gêneros, mostre isso
  if (loadingGenres) {
    return <div className="container"><h1>Carregando gêneros...</h1></div>;
  }

  // 5. O HTML (JSX) principal
  return (
    <div className="container">
      <header>
        <h1>Gerador de Álbuns</h1>
        <p>Escolha um gênero para sortearmos um álbum para você.</p>
      </header>
      
      <section className="genre-list">
        <h2>Gêneros</h2>
        {genres.map(genre => (
          // 6. Botões que chamam nossa nova função
          <button 
            key={genre.id} 
            onClick={() => handleGenreClick(genre.id)}
            disabled={loadingAlbum} // Desabilita o botão enquanto sorteia
          >
            {genre.name}
          </button>
        ))}
      </section>

      {/* 7. ÁREA DO RESULTADO (O Sorteio) */}
      <section className="album-result">
        {loadingAlbum && <h2>Sorteando...</h2>}
        
        {selectedAlbum && (
          <div className="album-card">
            <h3>Seu álbum do dia é:</h3>
            <h2>{selectedAlbum.title}</h2>
            <p>por {selectedAlbum.artist}</p>
            <img 
              src={selectedAlbum.coverUrl} 
              alt={`Capa do álbum ${selectedAlbum.title}`} 
            />
          </div>
        )}
      </section>
    </div>
  );
}

export default App;