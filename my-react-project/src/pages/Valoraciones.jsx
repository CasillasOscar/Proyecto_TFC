import React from 'react';
import {
  Container,
  Typography,
  Box,
  Paper,
  Avatar,
  Grid,
  Rating,
} from '@mui/material';

const valoraciones = [
  {
    id: 1,
    usuario: 'Lucía Martínez',
    comentario: '¡Todo perfecto! El producto llegó en muy buen estado y el vendedor fue muy amable.',
    puntuacion: 5,
    fecha: '01/04/2025',
  },
  {
    id: 2,
    usuario: 'Pedro Gómez',
    comentario: 'Buena experiencia. Entrega rápida y sin problemas.',
    puntuacion: 4,
    fecha: '28/03/2025',
  },
  {
    id: 3,
    usuario: 'Ana Ruiz',
    comentario: 'El artículo no era exactamente como en la foto, pero funcionaba bien.',
    puntuacion: 3,
    fecha: '20/03/2025',
  },
  {
    id: 4,
    usuario: 'Carlos Ortega',
    comentario: 'El vendedor respondió rápido y fue muy atento. Repetiría sin duda.',
    puntuacion: 5,
    fecha: '15/03/2025',
  },
  {
    id: 5,
    usuario: 'Marta Sánchez',
    comentario: 'Me encantó el empaque y la presentación. ¡Muy detallista!',
    puntuacion: 4,
    fecha: '10/03/2025',
  },
];

export default function Valoraciones() {
  return (
    <Container sx={{ py: 5 }}>
      <Typography variant="h4" gutterBottom textAlign="center">
        ⭐ Valoraciones recibidas
      </Typography>

      <Typography variant="body1" textAlign="center" mb={4} maxWidth="md" mx="auto">
        Aquí puedes ver lo que dicen otros usuarios después de completar una compra contigo. 
        Las opiniones ayudan a construir confianza en la comunidad.
      </Typography>

      <Grid container spacing={3}>
        {valoraciones.map((val) => (
          <Grid item xs={12} md={6} key={val.id}>
            <Paper elevation={3} sx={{ p: 3 }}>
              <Box sx={{ display: 'flex', alignItems: 'center', mb: 1 }}>
                <Avatar sx={{ bgcolor: 'primary.main', mr: 2 }}>
                  {val.usuario.charAt(0)}
                </Avatar>
                <Box>
                  <Typography variant="subtitle1">{val.usuario}</Typography>
                  <Typography variant="caption" color="text.secondary">
                    {val.fecha}
                  </Typography>
                </Box>
              </Box>

              <Rating value={val.puntuacion} readOnly sx={{ mb: 1 }} />

              <Typography variant="body2">{val.comentario}</Typography>
            </Paper>
          </Grid>
        ))}
      </Grid>
    </Container>
  );

}
