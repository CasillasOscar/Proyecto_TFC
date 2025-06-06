import { useState, useEffect, useCallback } from "react";
import {
  Box,
  Typography,
  Avatar,
  Button,
  Grid,
  Card,
  CardContent,
  Divider,
  IconButton,
  styled,
} from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";
import LogoutIcon from "@mui/icons-material/Logout";
import StarIcon from "@mui/icons-material/Star";

import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import L from "leaflet";
import { logout } from "../../backend/Auth/Auth";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { updateAvatar } from "../../backend/User/user";
import { UpdateUserPopup } from "../../components/Popups/UpdateUserPopup";

// Icono personalizado para Leaflet
const icon = L.icon({
  iconUrl: "https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon.png",
  iconSize: [25, 41],
  iconAnchor: [12, 41],
});

// Componente del mapa
function Mapa() {
  const [posicion, setPosicion] = useState(null);

  useEffect(() => {
    navigator.geolocation.getCurrentPosition(
      (position) => {
        setPosicion([position.coords.latitude, position.coords.longitude]);
      },
      (error) => {
        console.error("Error obteniendo la ubicación", error);
      }
    );
  }, []);

  if (!posicion)
    return (
      <Typography color="text.secondary">Obteniendo ubicación...</Typography>
    );

  return (
    <MapContainer
      center={posicion}
      zoom={13}
      style={{ height: "100%", width: "100%" }}
    >
      <TileLayer
        attribution='&copy; <a href="https://osm.org/copyright">OpenStreetMap</a>'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
      <Marker position={posicion} icon={icon}>
        <Popup>Estás aquí</Popup>
      </Marker>
    </MapContainer>
  );
}

const VisuallyHiddenInput = styled("input")({
  clip: "rect(0 0 0 0)",
  clipPath: "inset(50%)",
  height: 1,
  overflow: "hidden",
  position: "absolute",
  bottom: 0,
  left: 0,
  whiteSpace: "nowrap",
  width: 1,
});

export default function Perfil({
  user,
  handleUserChange,
  avatarUrl,
  getAvatarUrl,
}) {
  const navigate = useNavigate();
  const [openUpdateUserPopup, setOpenUpdateUserPopup] = useState(false);

  const usuario = {
    nombre: user ? user.nombre : "Usuario Anónimo",
    email: user ? user.email : "Email no disponible",
    telefono: user ? user.telefono : "Teléfono no disponible",
    provincia: user ? user.provincia : "Dirección no disponible",
    valoracion: user ? user.valoracion : 0,
  };

  const handleLogout = async () => {
    const response = await logout();
    console.log(response);
    if (response.status === 200) {
      localStorage.removeItem("token");
      localStorage.removeItem("refreshToken");
      localStorage.removeItem("user");
      localStorage.removeItem('favoritos')
      handleUserChange();
      toast.success(`${user.nombre} has cerrado sesión correctamente`);
      navigate("/login");
    } else {
      console.error("Error al cerrar sesión:", response.data.message);
    }
  };

  const handleUpdateAvatar = async (file) => {
    console.log(file);
    if (!file) {
      toast.error("No se ha seleccionado ningún archivo");
      return;
    }
    try {
      const response = await updateAvatar(user.nickname, file);
      if (response.status === 200) {
        toast.success("Avatar actualizado correctamente");
        getAvatarUrl();
      } else {
        console.error("Error al actualizar el avatar:", response.data.message);
        toast.error(
          "Error al actualizar el avatar. Por favor, inténtalo de nuevo más tarde."
        );
      }
    } catch (error) {
      console.error("Error al actualizar el avatar:", error);
      toast.error(
        "Error al actualizar el avatar. Por favor, inténtalo de nuevo más tarde."
      );
    }
  };

  const handleUserUpdatedInPopup = useCallback((updatedUserData) => {
    localStorage.setItem("user", JSON.stringify(updatedUserData));
    handleUserChange();
  }, [handleUserChange]);

  return (
    <Box sx={{ p: 4 }}>
      {/* Encabezado */}
      <Box sx={{ display: "flex", alignItems: "center", gap: 2, mb: 4 }}>
        <Button component="label">
          <Avatar src={avatarUrl} sx={{ width: 100, height: 100 }} />
          <VisuallyHiddenInput
            type="file"
            onChange={(event) => handleUpdateAvatar(event.target.files[0])}
            multiple={false}
            accept="image/jpg, image/jpeg, image/png"
          />
        </Button>
        <Box>
          <Typography variant="h5">{usuario.nombre}</Typography>
          <Typography variant="body1" color="text.secondary">
            {usuario.email}
          </Typography>
        </Box>
        <IconButton onClick={() => setOpenUpdateUserPopup(true)} color="primary" sx={{ ml: "auto" }}>
          <EditIcon />
        </IconButton>
      </Box>

      <Divider sx={{ mb: 4 }} />

      <Grid
        container
        spacing={2}
        justifyContent="center"
        alignItems="stretch"
      >
        {/* Información de contacto */}
        <Grid item xs={12} sm={5} md={4}>
          <Card sx={{ height: "100%" }}>
            <CardContent sx={{ textAlign: "center", height: "100%" }}>
              <Typography variant="h6">Información de contacto</Typography>
              <Typography>Teléfono: {usuario.telefono}</Typography>
              <Typography>
                Provincia:{" "}
                {usuario.provincia === "empty" ? "No definida" : usuario.provincia}
              </Typography>
            </CardContent>
          </Card>
        </Grid>

        {/* Valoración */}
        <Grid item xs={12} sm={5} md={4}>
          <Card sx={{ height: "100%" }}>
            <CardContent sx={{ textAlign: "center", height: "100%" }}>
              <Typography variant="h6">Valoración</Typography>
              <Box
                sx={{
                  display: "flex",
                  justifyContent: "center",
                  alignItems: "center",
                  gap: 1,
                }}
              >
                <StarIcon color="warning" />
                <Typography>{usuario.valoracion} / 5</Typography>
              </Box>
            </CardContent>
          </Card>
        </Grid>
      </Grid>

      {/* Mis productos publicados */}
      <Grid item xs={12} sx={{ mt: 2 }}>
        <Card>
          <CardContent sx={{ textAlign: "justify" }}>
            <Typography variant="h6">Mis productos publicados</Typography>
            <Typography color="text.secondary">Productos.</Typography>
          </CardContent>
        </Card>
      </Grid>

      {/* Mapa */}
      <Box sx={{ mt: 4 }}>
        <Card>
          <CardContent sx={{ textAlign: "center" }}>
            <Typography variant="h6" gutterBottom>
              Tu ubicación actual
            </Typography>
            <Box
              sx={{
                width: "100%",
                height: 400,
                borderRadius: 2,
                overflow: "hidden",
              }}
            >
              <Mapa />
            </Box>
          </CardContent>
        </Card>
      </Box>

      {/* Cerrar Sesión */}
      <Box sx={{ mt: 4, textAlign: "center" }}>
        <Button
          variant="outlined"
          color="error"
          startIcon={<LogoutIcon />}
          onClick={handleLogout}
        >
          Cerrar sesión
        </Button>
      </Box>

      {
        openUpdateUserPopup && (
          <UpdateUserPopup
            isOpen={openUpdateUserPopup}
            onClose={() => setOpenUpdateUserPopup(false)}
            onUserUpdated={handleUserUpdatedInPopup}
          />
        )
      }
    </Box >
  );
}
