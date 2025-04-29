import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';

import Home from './pages/Home';
import Favoritos from './pages/Favoritos';
import Perfil from './pages/Perfil';
import NuevoProducto from './pages/NuevoProducto';
import Login from './pages/Login';
import Register from './pages/Register';
import Recuperar from './pages/Recuperar';

export default function App() {
  return (
    <>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/favoritos" element={<Favoritos />} />
        <Route path="/perfil" element={<Perfil />} />
        <Route path="/nuevo" element={<NuevoProducto />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register/>} />
        <Route path="/recuperar" element={<Recuperar/>} />
      </Routes>
      <Footer />
    </>
  );
}
