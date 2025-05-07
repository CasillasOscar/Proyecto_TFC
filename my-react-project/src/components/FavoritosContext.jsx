import React, { createContext, useContext, useEffect, useState } from 'react';

const FavoritosContext = createContext();
export const useFavoritos = () => useContext(FavoritosContext);

export const FavoritosProvider = ({ children }) => {
  const [favoritos, setFavoritos] = useState([]);

  useEffect(() => {
    const guardados = JSON.parse(localStorage.getItem('favoritos')) || [];
    setFavoritos(guardados);
  }, []);

  useEffect(() => {
    localStorage.setItem('favoritos', JSON.stringify(favoritos));
  }, [favoritos]);

  const toggleFavorito = (id) => {
    setFavoritos((prev) =>
      prev.includes(id) ? prev.filter((fid) => fid !== id) : [...prev, id]
    );
  };

  const eliminarFavorito = (id) => {
    setFavoritos((prev) => prev.filter((fid) => fid !== id));
  };

  return (
    <FavoritosContext.Provider value={{ favoritos, toggleFavorito, eliminarFavorito }}>
      {children}
    </FavoritosContext.Provider>
  );
};
