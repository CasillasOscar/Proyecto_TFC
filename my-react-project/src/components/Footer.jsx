import React from 'react';
import { Box, Typography } from '@mui/material';

export default function Footer() {
  return (
    <Box sx={{ bgcolor: 'success.dark', color: 'white', p: 2, mt: 4 }} component="footer">
      <Typography variant="body2" align="center">
        © 2025 Supermercado App. Todos los derechos reservados.
      </Typography>
    </Box>
  );
}
