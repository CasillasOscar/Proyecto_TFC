import React, { useState } from 'react';
import { Box, Typography, TextField, Button, Input } from '@mui/material';

export default function NuevoProducto() {
  const [imagen, setImagen] = useState(null);
  const [preview, setPreview] = useState(null);

  const handleImagenChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setImagen(file);
      setPreview(URL.createObjectURL(file));
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    alert("Producto subido (simulado)");
  };

  return (
    <Box sx={{
      p: 3,
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
    }}>
      <Box 
        component="form" 
        onSubmit={handleSubmit} 
        sx={{ 
          display: 'flex', 
          flexDirection: 'column', 
          alignItems: 'center', 
          gap: 2, 
          maxWidth: 500 
        }}
      >
        <Typography variant="h5" mb={2} sx={{ textAlign: 'center' }}>
          📦 Añadir nuevo producto
        </Typography>

        <TextField label="Título del producto" required fullWidth />
        <TextField label="Precio (€)" type="number" required fullWidth />
        <TextField label="Descripción" multiline rows={4} required fullWidth />

        <Input type="file" onChange={handleImagenChange} accept="image/*" />

        {preview && (
          <Box mt={2}>
            <Typography variant="subtitle2">Vista previa:</Typography>
            <img src={preview} alt="preview" style={{ width: '100%', maxHeight: 300, objectFit: 'cover' }} />
          </Box>
        )}

        <Button variant="contained" type="submit" color="success" fullWidth>
          Publicar
        </Button>
      </Box>
    </Box>
  );
}
