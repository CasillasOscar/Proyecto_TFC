import React from 'react';
import { Box, Typography } from '@mui/material';

export default function Perfil() {
  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h5">👤 Mi Perfil</Typography>
      <Typography variant="body1" sx={{ mt: 2 }}>
        Información del usuario, ajustes, etc.
      </Typography>
    </Box>
  );
}
