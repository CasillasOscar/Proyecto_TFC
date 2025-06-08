import { useEffect, useState, useCallback } from "react"; // ¡Importa useCallback!
import { Routes, Route } from "react-router-dom";
import Header from "./components/Header";
import Footer from "./components/Footer";
import "leaflet/dist/leaflet.css";
import "react-toastify/dist/ReactToastify.css";
import { ToastContainer } from "react-toastify";
import { BrowserRouter } from "react-router-dom";

import Home from "./pages/Home";
import Favoritos from "./pages/Usuario/Favoritos";
import Perfil from "./pages/Usuario/Perfil";
import NuevoProducto from "./pages/NuevoProducto";
import Login from "./pages/Auth/Login";
import Register from "./pages/Auth/Register";
import Recuperar from "./pages/Auth/Recuperar";
import AcercaDe from "./pages/AcercaDe";
import Valoraciones from "./pages/Valoraciones";
import { getAvatar, listFavorites } from "./backend/User/user";
import { Producto } from "./pages/Producto/Producto";
import { MisProductos } from "./pages/Usuario/MisProductos";
import { Box } from "@mui/material";
import ScrollToTop from "./components/configureViews/ScrollToTop"

export default function App() {
  const [user, setUser] = useState(null);
  const [favoritos, setFavoritos] = useState(null);
  const [avatarUrl, setAvatarUrl] = useState("");

  const handleUserChange = useCallback(async () => {
    const userInStorage = localStorage.getItem("user");
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

  const fetchFavoritesUser = useCallback(async () => {
    try {
      const response = await listFavorites(user.nickname);
      if (response.status === 200) {
        setFavoritos(response.data.favorites_products);
      }
    } catch (error) {
      console.error("Error al obtener los favoritos:", error);
    }
  }, [user]);

  useEffect(() => {
    try {
      localStorage.setItem("favoritos", JSON.stringify(favoritos));
    } catch (e) {
      console.error("Error al guardar favoritos en localStorage:", e);
    }
  }, [favoritos]);

  useEffect(() => {
    if (user) {
      getAvatarUrl();
      fetchFavoritesUser();
    }
  }, [user, getAvatarUrl, fetchFavoritesUser]);

  useEffect(() => {
    handleUserChange();
  }, [handleUserChange]);

  return (
    <BrowserRouter>
    <ScrollToTop/>
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          minHeight: "100vh", 
        }}
      >
        <ToastContainer
          position="top-right"
          autoClose={2500}
          hideProgressBar={true}
          newestOnTop={false}
          closeOnClick
          rtl={false}
          draggable
          theme="light"
        />

        <Header user={user} avatarUrl={avatarUrl} />
        <Box component="main" sx={{ flexGrow: 1 }}>
          <Routes>
            <Route
              path="/"
              element={
                <Home
                  user={user}
                  favoritos={favoritos}
                  setFavoritos={setFavoritos}
                />
              }
            />
            <Route
              path="/favoritos"
              element={
                <Favoritos
                  user={user}
                  favoritos={favoritos}
                  setFavoritos={setFavoritos}
                />
              }
            />
            <Route
              path="/perfil"
              element={
                <Perfil
                  user={user}
                  handleUserChange={handleUserChange}
                  getAvatarUrl={getAvatarUrl}
                  avatarUrl={avatarUrl}
                />
              }
            />
            <Route path="/nuevo" element={<NuevoProducto />} />
            <Route
              path="/login"
              element={
                <Login
                  handleUserChange={handleUserChange}
                  getAvatarFn={getAvatar}
                />
              }
            />
            <Route
              path="/register"
              element={<Register handleUserChange={handleUserChange} />}
            />
            <Route path="/recuperar" element={<Recuperar />} />
            <Route path="/AcercaDe" element={<AcercaDe />} />
            <Route path="/Valoraciones" element={<Valoraciones />} />
            <Route
              path="/producto/:id"
              element={
                <Producto user={user} handleUserChange={handleUserChange} />
              }
            />
            <Route
              path="/perfil/misProductos/:nickname"
              element={<MisProductos user={user} />}
            />
          </Routes>
        </Box>
        <Footer user={user} />
      </Box>
    </BrowserRouter>
  );
}
