import React from 'react';
import { Card, CardContent, Typography, CardActions, Button } from '@mui/material';
import { Link } from 'react-router-dom';

export default function ProductCard({ producto }) {
  return (
    <Card>
      <CardContent>
        <Typography variant="h6">{producto.nombre}</Typography>
        <Typography color="text.secondary">${producto.precio}</Typography>
        <Typography variant="body2" sx={{ mt: 1 }}>{producto.categoria}</Typography>
      </CardContent>
      <CardActions>
        <Button component={Link} to={`/producto/${producto.id}`} size="small">
          Ver más
        </Button>
      </CardActions>
    </Card>
  );
}

  