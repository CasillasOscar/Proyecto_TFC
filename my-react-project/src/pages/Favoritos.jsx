import React from 'react';
import {
  Box, Grid, Card, CardMedia, CardContent, Typography, IconButton
} from '@mui/material';
import FavoriteIcon from '@mui/icons-material/Favorite';
import { useFavoritos } from '../components/FavoritosContext.jsx';

const productos = Array.from({ length: 20 }, (_, i) => ({
  id: i + 1,
  nombre: `Producto ${i + 1}`,
  precio: `${(10 + i * 2).toFixed(2)} €`,
  imagen: `https://picsum.photos/300/200?random=${i + 1}`,
}));

export default function Favoritos() {
  const { favoritos, eliminarFavorito } = useFavoritos();
  const productosFavoritos = productos.filter(p => favoritos.includes(p.id));

  return (
    <Box sx={{ p: 4 }}>
      <Typography variant="h4" gutterBottom textAlign="center">
        ❤️ Productos guardados
      </Typography>

      {productosFavoritos.length === 0 ? (
        <Typography textAlign="center" sx={{ mt: 4 }} color="text.secondary">
          No has guardado ningún producto como favorito todavía.
        </Typography>
      ) : (
        <Grid container spacing={3} justifyContent="center">
          {productosFavoritos.map((producto) => (
            <Grid item xs={12} sm={6} md={4} lg={3} key={producto.id}>
              <Card sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
                <CardMedia component="img" height="200" image={producto.imagen} alt={producto.nombre} />
                <CardContent sx={{ flexGrow: 1 }}>
                  <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Typography variant="h6">{producto.nombre}</Typography>
                    <IconButton color="error" onClick={() => eliminarFavorito(producto.id)}>
                      <FavoriteIcon />
                    </IconButton>
                  </Box>
                  <Typography variant="body2" color="text.secondary">
                    Precio: {producto.precio}
                  </Typography>
                </CardContent>
              </Card>
            </Grid>
          ))}
        </Grid>
      )}
    </Box>
  );
}
