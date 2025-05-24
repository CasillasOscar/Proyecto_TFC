import React, { useState, useEffect } from 'react';
import {
  Box, Typography, Avatar, Button, Grid, Card, CardContent, Divider, IconButton,
} from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import LogoutIcon from '@mui/icons-material/Logout';
import FavoriteIcon from '@mui/icons-material/Favorite';
import StarIcon from '@mui/icons-material/Star';

import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import L from 'leaflet';
import { logout } from '../backend/Auth/Auth';
import { useNavigate } from 'react-router-dom';

// Icono personalizado para Leaflet
const icon = L.icon({
  iconUrl: 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon.png',
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



  if (!posicion) return <Typography color="text.secondary">Obteniendo ubicación...</Typography>;

  return (
    <MapContainer center={posicion} zoom={13} style={{ height: '100%', width: '100%' }}>
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

export default function Perfil({user, handleUserChange}) {

  const navigate = useNavigate();
  
  const usuario = {
    nombre: user.nombre,
    email: user.email,
    telefono: user.telefono,
    direccion: "Avenida Fuenlabrada 103 2A, Leganés, Madrid",
    avatar: "/src/assets/chr.jpeg",
    valoracion: user.valoracion,
  };

  const handleLogout = async () => {
    const response = await logout();
    console.log(response);
    if (response.status === 200) {
      localStorage.removeItem('token');
      localStorage.removeItem('refreshToken');
      localStorage.removeItem('user');
      handleUserChange();
      navigate('/login');
    } else {
      console.error("Error al cerrar sesión:", response.data.message);
    }
    
  }

  return (
    <Box sx={{ p: 4 }}>
      {/* Encabezado */}
      <Box sx={{ display: 'flex', alignItems: 'center', gap: 2, mb: 4 }}>
        <Avatar src={usuario.avatar} sx={{ width: 100, height: 100 }} />
        <Box>
          <Typography variant="h5">{usuario.nombre}</Typography>
          <Typography variant="body1" color="text.secondary">{usuario.email}</Typography>
        </Box>
        <IconButton color="primary" sx={{ ml: 'auto' }}>
          <EditIcon />
        </IconButton>
      </Box>

      <Divider sx={{ mb: 4 }} />

      {/* Informacion de contacto */}
      <Grid container spacing={2} justifyContent="center">
        <Grid item xs={12} md={6} sx={{ display: 'flex' }}>
          <Card sx={{ flex: 1 }}>
            <CardContent sx={{ textAlign: 'justify' }}>
              <Typography variant="h6">Información de contacto</Typography>
              <Typography>Teléfono: {usuario.telefono}</Typography>
              <Typography>Dirección: {usuario.direccion}</Typography>
            </CardContent>
          </Card>
        </Grid>

        {/* Valoración */}
        <Grid item xs={12} md={6} sx={{ display: 'flex' }}>
          <Card sx={{ flex: 1 }}>
            <CardContent sx={{ textAlign: 'justify' }}>
              <Typography variant="h6">Valoración</Typography>
              <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                <StarIcon color="warning" />
                <Typography>{usuario.valoracion} / 5</Typography>
              </Box>
            </CardContent>
          </Card>
        </Grid>

        {/* Favoritos */}
        <Grid item xs={12} md={8} sx={{ display: 'flex' }}>
          <Card sx={{ flex: 1 }}>
            <CardContent sx={{ textAlign: 'justify' }}>
              <Typography variant="h6" gutterBottom>
                Favoritos recientes
              </Typography>
              <Box sx={{ display: 'flex', gap: 2 }}>
                <FavoriteIcon color="error" />
              </Box>
            </CardContent>
          </Card>
        </Grid>

        {/* Productos */}
        <Grid item xs={12} md={8} sx={{ display: 'flex' }}>
          <Card sx={{ flex: 1 }}>
            <CardContent sx={{ textAlign: 'justify' }}>
              <Typography variant="h6">Mis productos publicados</Typography>
              <Typography color="text.secondary">
                Productos.
              </Typography>
            </CardContent>
          </Card>
        </Grid>
      </Grid>

      {/* Mapa */}
      <Box sx={{ mt: 4 }}>
        <Card>
          <CardContent sx={{ textAlign: 'center' }}>
            <Typography variant="h6" gutterBottom>
              Tu ubicación actual
            </Typography>
            <Box sx={{ width: '100%', height: 400, borderRadius: 2, overflow: 'hidden' }}>
              <Mapa />
            </Box>
          </CardContent>
        </Card>
      </Box>

      {/* Cerrar Sesión */}
      <Box sx={{ mt: 4, textAlign: 'center' }}>
        <Button variant="outlined" color="error" startIcon={<LogoutIcon />} onClick={handleLogout}>
          Cerrar sesión
        </Button>
      </Box>
    </Box>
  );
}
