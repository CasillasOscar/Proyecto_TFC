import React, { useState } from 'react';
import {
  Card, CardMedia, CardContent, Typography,
  CardActions, Button, Dialog, DialogTitle,
  DialogContent, DialogContentText, DialogActions
} from '@mui/material';

export default function ProductCard({ product }) {
  const [open, setOpen] = useState(false);

  const handleDialogOpen = () => setOpen(true);
  const handleDialogClose = () => setOpen(false);

  return (
    <>
      <Card>
        <CardMedia
          component="img"
          height="140"
          image={product.image}
          alt={product.name}
        />
        <CardContent>
          <Typography variant="h6">{product.name}</Typography>
          <Typography color="text.secondary">${product.price.toFixed(2)}</Typography>
        </CardContent>
        <CardActions>
          <Button size="small" onClick={handleDialogOpen}>Ver más</Button>
          <Button size="small" variant="contained" color="success">Añadir</Button>
        </CardActions>
      </Card>

      <Dialog open={open} onClose={handleDialogClose}>
        <DialogTitle>Detalles de {product.name}</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Aquí podrías poner más detalles del producto, como su descripción, disponibilidad o ingredientes.
          </DialogContentText>
          <img src={product.image} alt={product.name} style={{ width: '100%', marginTop: 16, borderRadius: 8 }} />
          <Typography sx={{ mt: 2 }}><strong>Precio:</strong> ${product.price.toFixed(2)}</Typography>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleDialogClose}>Cerrar</Button>
          <Button onClick={() => alert('Producto añadido')} variant="contained" color="success">
            Añadir al carrito
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
}

  