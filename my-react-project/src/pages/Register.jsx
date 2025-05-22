import React, { useState } from 'react';
import { TextField, Button, Box, Typography, Paper } from '@mui/material';
import { signIn } from '../backend/Auth/Auth';
import { useNavigate } from 'react-router-dom';

export default function RegisterPage() {

  const navigate = useNavigate();

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

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      console.log(formData);
      const response = await signIn(formData.nickname, formData.nombre, formData.apellido, formData.password, formData.telefono, formData.email);

      if (response.status === 200) {
        navigate("/login");
      }

    } catch (error) {
      if (error.status === 400) {
        console.log(error);
        toast.error("Error al iniciar sesión: " + error.response.data.error);
      }
    }
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
