import React, { useState, useEffect } from 'react';

import './App.css'; 

function App() {
  const [genres, setGenres] = useState([]);
  const [loadingGenres, setLoadingGenres] = useState(true);

  const [selectedAlbum, setSelectedAlbum] = useState(null);
  const [loadingAlbum, setLoadingAlbum] = useState(false);

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

  const handleGenreClick = async (genreId) => {
    try {
      setLoadingAlbum(true);
      setSelectedAlbum(null);

      const response = await fetch(`http://localhost:8080/api/albums/random?genreId=${genreId}`);
      
      if (response.status === 404) {
        alert("Ops! Não temos álbuns cadastrados para este gênero.");
        setSelectedAlbum(null);
      } else if (response.ok) {
        const album = await response.json();
        setSelectedAlbum(album);
      } else {
        throw new Error("Falha na requisição");
      }

    } catch (error) {
      console.error("Erro ao sortear álbum:", error);
      alert("Ocorreu um erro ao sortear.");
    } finally {
      setLoadingAlbum(false);
    }
  };

  
  if (loadingGenres) {
    return <div className="container"><h1>Carregando gêneros...</h1></div>;
  }

  return (
    <div className="container">
      <header>
        <h1>Gerador de Álbuns</h1>
        <p>Escolha um gênero para sortearmos um álbum para você.</p>
      </header>
      
      <section className="genre-list">
        <h2>Gêneros</h2>
        {genres.map(genre => (
          <button 
            key={genre.id} 
            onClick={() => handleGenreClick(genre.id)}
            disabled={loadingAlbum}
          >
            {genre.name}
          </button>
        ))}
      </section>

      {/*(Sorteio)*/}
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