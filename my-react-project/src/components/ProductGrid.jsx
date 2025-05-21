import React from 'react';
import { Grid } from '@mui/material';
import ProductCard from './ProductCard';

const products = [
  { id: 1, name: "Peras", price: 2.5, image: "https://via.placeholder.com/150" },
  { id: 2, name: "Leche", price: 1.2, image: "https://via.placeholder.com/150" },
  { id: 3, name: "Pan", price: 1.0, image: "https://via.placeholder.com/150" },
  { id: 4, name: "Huevos", price: 3.0, image: "https://via.placeholder.com/150" },
];

export default function ProductGrid() {
  return (
    <Grid container spacing={3}>
      {products.map(product => (
        <Grid item xs={12} sm={6} md={3} key={product.id}>
          <ProductCard product={product} />
        </Grid>
      ))}
    </Grid>
  );
}
