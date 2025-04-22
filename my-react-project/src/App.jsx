import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';

import Home from './pages/Home';
import Favoritos from './pages/Favoritos';
import Perfil from './pages/Perfil';
import NuevoProducto from './pages/NuevoProducto';

export default function App() {
  return (
    <>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/favoritos" element={<Favoritos />} />
        <Route path="/perfil" element={<Perfil />} />
        <Route path="/nuevo" element={<NuevoProducto />} />
      </Routes>
      <Footer />
    </>
  );
}
