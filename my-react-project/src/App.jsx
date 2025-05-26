import React, { useEffect, useState, useCallback } from 'react'; // ¡Importa useCallback!
import { Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';
import 'leaflet/dist/leaflet.css';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from 'react-toastify'; 
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
import { getAvatar } from './backend/User/user';


export default function App() {
  const [user, setUser] = useState(null); 
  const [avatarUrl, setAvatarUrl] = useState("");

  const handleUserChange = useCallback(async () => { 
    const userInStorage = localStorage.getItem('user');
    if (!userInStorage) {
      setUser(null);
      return;
    }
    setUser(JSON.parse(userInStorage));
  }, []); 

 
  const getAvatarUrl = useCallback(async () => {
    if (user && user.nickname) {
      try {
        const imgUrl = await getAvatar(user.nickname);
        if (imgUrl) {
          setAvatarUrl(imgUrl);
        } else {
          console.error(
            "Error al obtener el avatar: No se ha podido recuperar la imagen"
          );
        }
      } catch (error) {
        console.error("Error al obtener el avatar:", error);
      }
    }
  }, [user]); 

  
  useEffect(() => {
    if (user) { 
      getAvatarUrl();
    }
  }, [user, getAvatarUrl]); 

 
  useEffect(() => {
    handleUserChange(); 
  }, [handleUserChange]); 


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

      <Header user={user} avatarUrl={avatarUrl}/>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/favoritos" element={<Favoritos/>} />
        <Route path="/perfil" element={<Perfil user={user} handleUserChange={handleUserChange} getAvatarUrl={getAvatarUrl} avatarUrl={avatarUrl}/>} />
        <Route path="/nuevo" element={<NuevoProducto />} />
        <Route path="/login" element={<Login handleUserChange={handleUserChange} getAvatarFn={getAvatar}/>} /> 
        <Route path="/register" element={<Register handleUserChange={handleUserChange}/>} />
        <Route path="/recuperar" element={<Recuperar />} />
        <Route path="/AcercaDe" element={<AcercaDe />} />
        <Route path="/Valoraciones" element={<Valoraciones />} />
      </Routes>
      <Footer user={user}/>
    </BrowserRouter>
  );
}