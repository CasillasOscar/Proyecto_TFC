import React from 'react';
import App from './App.jsx';
import { createRoot } from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import { FavoritosProvider } from './components/FavoritosContext'; // ⬅️ Importamos el Provider
import './index.css';

createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <FavoritosProvider> {/* ⬅️ Envolvemos toda la app */}
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </FavoritosProvider>
  </React.StrictMode>
);
