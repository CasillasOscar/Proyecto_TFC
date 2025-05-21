import React, { useState } from 'react';
import { TextField, Button, Box, Typography, Paper } from '@mui/material';

export default function RegisterPage() {
  const [formData, setFormData] = useState({
    nickname: '',
    nombre: '',
    apellido: '',
    email: '',
    telefono: '',
    password: '',
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleRegister = (e) => {
    e.preventDefault();
    console.log(formData);
  };

  return (
    <Box
      sx={{
        minHeight: '80vh',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
      }}
    >
      <Paper elevation={6} sx={{ padding: 4, width: '100%', maxWidth: 500 }}>
        <Typography variant="h5" mb={3} textAlign="center">
          Crear Cuenta
        </Typography>
        <form onSubmit={handleRegister}>
          <TextField
            label="Nickname"
            name="nickname"
            fullWidth
            margin="normal"
            required
            value={formData.nickname}
            onChange={handleChange}
          />
          <TextField
            label="Nombre"
            name="nombre"
            fullWidth
            margin="normal"
            required
            value={formData.nombre}
            onChange={handleChange}
          />
          <TextField
            label="Apellido"
            name="apellido"
            fullWidth
            margin="normal"
            required
            value={formData.apellido}
            onChange={handleChange}
          />
          <TextField
            label="Correo electrónico"
            type="email"
            name="email"
            fullWidth
            margin="normal"
            required
            value={formData.email}
            onChange={handleChange}
          />
          <TextField
            label="Teléfono"
            name="telefono"
            fullWidth
            margin="normal"
            required
            value={formData.telefono}
            onChange={handleChange}
          />
          <TextField
            label="Contraseña"
            type="password"
            name="password"
            fullWidth
            margin="normal"
            required
            value={formData.password}
            onChange={handleChange}
          />
          <Button
            type="submit"
            variant="contained"
            color="success"
            fullWidth
            sx={{ mt: 2 }}
          >
            Registrarse
          </Button>
        </form>
      </Paper>
    </Box>
  );
}
