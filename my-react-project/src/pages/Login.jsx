import React, { useState } from 'react';
import { TextField, Button, Box, Typography, Paper, Stack } from '@mui/material';
import { useNavigate } from 'react-router-dom';

export default function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();
    console.log({ email, password });
  };

  return (
    <Box
      sx={{
        minHeight: '80vh',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#f5f5f5',
      }}
    >
      <Paper elevation={6} sx={{ padding: 4, width: '100%', maxWidth: 400 }}>
        <Typography variant="h5" mb={3} textAlign="center">
          Iniciar Sesión
        </Typography>
        <form onSubmit={handleLogin}>
          <Stack spacing={2}>
            <TextField
              label="Correo electrónico"
              type="email"
              fullWidth
              required
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            <TextField
              label="Contraseña"
              type="password"
              fullWidth
              required
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />

            <Button
              type="submit"
              variant="contained"
              color="primary"
              fullWidth
            >
              Entrar
            </Button>

            <Button
              variant="text"
              color="error"
              fullWidth
              onClick={() => navigate('/recuperar')}
            >
              ¿Has olvidado tu contraseña?
            </Button>

            <Button
              variant="outlined"
              color="secondary"
              fullWidth
              onClick={() => navigate('/register')}
            >
              ¿No tienes cuenta? Regístrate
            </Button>
          </Stack>
        </form>
      </Paper>
    </Box>
  );
}
