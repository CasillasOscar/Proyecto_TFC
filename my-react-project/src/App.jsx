import React, { useEffect, useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';
import 'leaflet/dist/leaflet.css';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css'; 
import { BrowserRouter } from 'react-router-dom';

import Home from './pages/Home';
import Favoritos from './pages/Usuario/Favoritos';
import Perfil from './pages/Usuario/Perfil';
import NuevoProducto from './pages/NuevoProducto';
import Login from './pages/Auth/Login';
import Register from './pages/Auth/Register';
import Recuperar from './pages/Auth/Recuperar';
import AcercaDe from './pages/AcercaDe';
import Valoraciones from './pages/Valoraciones';

export default function App() {
  const[user, setUser] = useState();

  const handleUserChange = () => {
    if (!localStorage.getItem('user')) {
      setUser(null)
      return
    }
    setUser(JSON.parse(localStorage.getItem('user')));
  }

  useEffect(() => {
    handleUserChange();
  }, []);
  

  return (
    <BrowserRouter>
      <ToastContainer
        position="top-right" 
        autoClose={2500}    
        hideProgressBar={true} 
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="light" 
      />

      <Header user={user}/>
      <Routes>
        <Route path="/" element={<Home />} />

        <Route path="/favoritos" element={<Favoritos/>} />
        <Route path="/perfil" element={<Perfil user={user} handleUserChange={handleUserChange}/>} />
        <Route path="/nuevo" element={<NuevoProducto />} />
        <Route path="/login" element={<Login handleUserChange={handleUserChange}/>} />
        <Route path="/register" element={<Register handleUserChange={handleUserChange}/>} />
        <Route path="/recuperar" element={<Recuperar />} />
        <Route path="/AcercaDe" element={<AcercaDe />} />
        <Route path="/Valoraciones" element={<Valoraciones />} />
      </Routes>
      <Footer user={user}/>
    </BrowserRouter>
  );
}
