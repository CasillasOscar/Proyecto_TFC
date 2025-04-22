import React from 'react';
import { Box, Typography } from '@mui/material';

export default function Footer() {
  return (
    <Box
      sx={{
        mt: 'auto',
        p: 2,
        backgroundColor: '#f5f5f5',
        textAlign: 'center',
        borderTop: '1px solid #ddd',
      }}
    >
      <Typography variant="body2" color="text.secondary">
        © 2025 ReUsa. Todos los derechos reservados.
      </Typography>
    </Box>
  );
}
