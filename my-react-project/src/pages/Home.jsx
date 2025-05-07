import React, { useState } from 'react';
import {
  Box, Typography, Grid, Card, CardMedia, CardContent, IconButton,
  Dialog, DialogTitle, DialogContent, DialogContentText, Button
} from '@mui/material';
import { Favorite, FavoriteBorder } from '@mui/icons-material';
import { useFavoritos } from '../components/FavoritosContext.jsx';

const productos = Array.from({ length: 20 }, (_, i) => ({
  id: i + 1,
  nombre: `Producto ${i + 1}`,
  precio: `${(10 + i * 2).toFixed(2)} €`,
  imagen: `https://picsum.photos/300/200?random=${i + 1}`,
  descripcion: `Este es un producto de ejemplo con id ${i + 1}.`,
}));

export default function Home() {
  const [productoSeleccionado, setProductoSeleccionado] = useState(null);
  const { favoritos, toggleFavorito } = useFavoritos();

  return (
    <Box sx={{ p: 3, display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
      <Typography variant="h5" gutterBottom textAlign="center">
        🛍️ Productos disponibles
      </Typography>

      <Grid container spacing={2} justifyContent="center">
        {productos.map((producto) => (
          <Grid item xs={12} sm={6} md={4} lg={3} key={producto.id}>
            <Card sx={{ position: 'relative' }}>
              <IconButton
                onClick={() => toggleFavorito(producto.id)}
                sx={{
                  position: 'absolute',
                  top: 8,
                  right: 8,
                  backgroundColor: 'white',
                  '&:hover': { backgroundColor: '#eee' },
                }}
              >
                {favoritos.includes(producto.id) ? <Favorite color="error" /> : <FavoriteBorder />}
              </IconButton>

              <CardMedia component="img" height="200" image={producto.imagen} alt={producto.nombre} />

              <CardContent>
                <Typography variant="subtitle1">{producto.nombre}</Typography>
                <Typography variant="body2" color="text.secondary">{producto.precio}</Typography>
                <Box sx={{ mt: 2, textAlign: 'center' }}>
                  <Button variant="outlined" size="small" onClick={() => setProductoSeleccionado(producto)}>
                    🔍 Ver más
                  </Button>
                </Box>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>

      <Dialog open={Boolean(productoSeleccionado)} onClose={() => setProductoSeleccionado(null)} maxWidth="sm" fullWidth>
        {productoSeleccionado && (
          <>
            <DialogTitle>{productoSeleccionado.nombre}</DialogTitle>
            <DialogContent>
              <CardMedia
                component="img"
                height="200"
                image={productoSeleccionado.imagen}
                alt={productoSeleccionado.nombre}
                sx={{ mb: 2 }}
              />
              <DialogContentText><strong>Precio:</strong> {productoSeleccionado.precio}</DialogContentText>
              <DialogContentText sx={{ mt: 1 }}>
                <strong>Descripción:</strong> {productoSeleccionado.descripcion}
              </DialogContentText>
              <Box sx={{ mt: 3, textAlign: 'right' }}>
                <Button onClick={() => setProductoSeleccionado(null)} variant="contained">
                  Cerrar
                </Button>
              </Box>
            </DialogContent>
          </>
        )}
      </Dialog>
    </Box>
  );
}
