import React from 'react';
import { Container, Typography, Box } from '@mui/material';
import Header from './components/Header';
import ProductGrid from './components/ProductGrid';
import Footer from './components/Footer';

export default function App() {
  return (
    <>
      <Header />
      <Box sx={{ backgroundColor: '#FFF59D', py: 3 }}>
        <Typography variant="h5" align="center">
          🛍️ ¡Aprovechá las ofertas del día!
        </Typography>
      </Box>
      <Container sx={{ py: 4 }}>
        <ProductGrid />
      </Container>
      <Footer />
    </>
  );
}
