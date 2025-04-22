import React from 'react';
import { Box, Typography } from '@mui/material';

export default function Favoritos() {
  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h5">❤️ Productos guardados</Typography>
      <Typography variant="body1" sx={{ mt: 2 }}>
        Aquí aparecerán los productos que marcaste como favoritos.
      </Typography>
    </Box>
  );
}
