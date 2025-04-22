import React from 'react';
import { Box, Typography, Grid, Card, CardMedia, CardContent } from '@mui/material';

const productos = Array.from({ length: 20 }, (_, i) => ({
  id: i + 1,
  nombre: `Producto ${i + 1}`,
  precio: `${(10 + i * 2).toFixed(2)} €`,
  imagen: `https://picsum.photos/300/200?random=${i + 1}`,
}));

export default function Home() {
  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h6" gutterBottom>
        🛍️ Productos disponibles
      </Typography>

      <Grid container spacing={2}>
        {productos.map((producto) => (
          <Grid item xs={12} sm={6} md={4} lg={3} key={producto.id}>
            <Card>
              <CardMedia
                component="img"
                height="200"
                image={producto.imagen}
                alt={producto.nombre}
              />
              <CardContent>
                <Typography variant="subtitle1">{producto.nombre}</Typography>
                <Typography variant="body2" color="text.secondary">
                  {producto.precio}
                </Typography>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Box>
  );
}
