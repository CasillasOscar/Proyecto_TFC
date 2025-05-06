import React from 'react';
import {
  Box, Typography, Avatar, Button, Grid, Card, CardContent, Divider, IconButton,
} from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import LogoutIcon from '@mui/icons-material/Logout';
import FavoriteIcon from '@mui/icons-material/Favorite';
import StarIcon from '@mui/icons-material/Star';

export default function Perfil() {
  const usuario = {
    nombre: "Christian Mengibar",
    email: "christian_lega99@hotmail.com",
    telefono: "+34 629 964 320",
    direccion: "Avenida Fuenlabrada 103 2A, Leganés, Madrid",
    avatar: "/src/assets/chr.jpeg",
    valoracion: 4.5,
  };

  return (
    <Box sx={{ p: 4 }}>
      {/* Encabezado con avatar y nombre */}
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

      {/* Contenedor centrado */}
      <Grid container spacing={2} justifyContent="center">
        <Grid item xs={12} md={6}>
          <Card sx={{ height: '100%' }}>
            <CardContent sx={{ textAlign: 'justify' }}>
              <Typography variant="h6">Información de contacto</Typography>
              <Typography>Teléfono: {usuario.telefono}</Typography>
              <Typography>Dirección: {usuario.direccion}</Typography>
            </CardContent>
          </Card>
        </Grid>

        <Grid item xs={12} md={6}>
          <Card sx={{ height: '100%' }}>
            <CardContent sx={{ textAlign: 'justify' }}>
              <Typography variant="h6">Valoración</Typography>
              <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                <StarIcon color="warning" />
                <Typography>{usuario.valoracion} / 5</Typography>
              </Box>
            </CardContent>
          </Card>
        </Grid>

        <Grid item xs={12} md={8}>
          <Card sx={{ height: '100%' }}>
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

        <Grid item xs={12} md={8}>
          <Card sx={{ height: '100%' }}>
            <CardContent sx={{ textAlign: 'justify' }}>
              <Typography variant="h6">Mis productos publicados</Typography>
              <Typography color="text.secondary">
                Productos.
              </Typography>
            </CardContent>
          </Card>
        </Grid>
      </Grid>

      {/* Cerrar sesión */}
      <Box sx={{ mt: 4, textAlign: 'center' }}>
        <Button variant="outlined" color="error" startIcon={<LogoutIcon />}>
          Cerrar sesión
        </Button>
      </Box>
    </Box>
  );
}
