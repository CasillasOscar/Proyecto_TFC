import React, { useState } from 'react';
import { Box, Typography, Paper, TextField, Button, Stack } from '@mui/material';
import { useNavigate } from 'react-router-dom';

export default function RecuperarPage() {
  const [email, setEmail] = useState('');
  const [openHelperText, setOpenHelperText] = useState(true)
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    setOpenHelperText(true)
    console.log('Enviar enlace a:', email);
  };

  return (
    <Box
      sx={{
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        height:'60vh',
      }}
    >
      <Paper elevation={6} sx={{ padding: 4, width: '100%', maxWidth: 400, borderRadius: 3 }}>
        <Button onClick={() => navigate(-1)} sx={{ mb: 2 }} size="small">
          ← Volver
        </Button>

        <Typography variant="h5" fontWeight="bold" gutterBottom>
          Restablece tu contraseña
        </Typography>
        <Typography variant="body2" color="text.secondary" mb={3}>
          Introduce el e-mail vinculado a tu cuenta. Allí recibirás un enlace para restablecer la contraseña.
        </Typography>

        <form onSubmit={handleSubmit}>
          <Stack spacing={2}>
            <TextField
              label="Dirección de e-mail"
              type="email"
              fullWidth
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              helperText={openHelperText && 'Próximamente se desarrollará funcionalidad'}
              error={true}
              disabled={true}
            />

            <Button
              type="submit"
              variant="contained"
              fullWidth
              sx={{
                backgroundColor: '#1de9b6',
                color: 'black',
                '&:hover': {
                  backgroundColor: '#00bfa5',
                },
                borderRadius: 10,
                py: 1.5,
              }}
              disabled='true'
            >
              Enviar e-mail
            </Button>
          </Stack>
        </form>
      </Paper>
    </Box>
  );
}
