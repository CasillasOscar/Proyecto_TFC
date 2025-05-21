import React from 'react';
import { Box, Typography, Link, Grid, IconButton } from '@mui/material';
import FacebookIcon from '@mui/icons-material/Facebook';
import TwitterIcon from '@mui/icons-material/Twitter';
import InstagramIcon from '@mui/icons-material/Instagram';
import { useNavigate } from 'react-router-dom';

export default function Footer() {
  const navigate = useNavigate();

  const links = [
    { label: 'Inicio', path: '/' },
    { label: 'Favoritos', path: '/favoritos' },
    { label: 'Perfil', path: '/perfil' },
    { label: 'Valoraciones', path: '/valoraciones' },
    { label: 'Acerca de', path: '/acercade' },
  ];

  return (
    <Box
      component="footer"
      sx={{
        mt: 4,
        px: 3,
        py: 4,
        backgroundColor: '#f1f1f1',
        color: '#333',
        borderTop: '1px solid #ddd',
      }}
    >
      {/* Navegación */}
      <Grid container spacing={2} justifyContent="center">
        {links.map((link) => (
          <Grid item key={link.path}>
            <Link
              onClick={() => navigate(link.path)}
              underline="hover"
              sx={{
                color: '#555',
                fontSize: '0.9rem',
                cursor: 'pointer',
                '&:hover': { color: '#2e7d32' },
              }}
            >
              {link.label}
            </Link>
          </Grid>
        ))}
      </Grid>

      {/* App stores y redes sociales */}
      <Grid
        container
        spacing={2}
        justifyContent="space-between"
        alignItems="center"
        sx={{ mt: 3, px: { xs: 1, sm: 5 } }}
      >
        <Grid item xs={12} sm={6} sx={{ textAlign: 'center' }}>
          <a
            href="https://www.apple.com/es/app-store/"
            target="_blank"
            rel="noopener noreferrer"
          >
            <Box component="img" src="src/assets/apple.png" alt="Apple Store" sx={{ height: 50, mx: 1 }} />
          </a>

          <a
            href="https://play.google.com/store/games?hl=es"
            target="_blank"
            rel="noopener noreferrer"
          >
            <Box component="img" src="src/assets/google.png" alt="Google Play" sx={{ height: 50, mx: 1 }} />
          </a>
        </Grid>


        {/* Redes sociales */}
        <Grid item xs={12} sm={6} sx={{ textAlign: 'center', mt: { xs: 2, sm: 0 } }}>
          <IconButton href="https://facebook.com" target="_blank" sx={{ color: '#555' }}>
            <FacebookIcon />
          </IconButton>
          <IconButton href="https://twitter.com" target="_blank" sx={{ color: '#555' }}>
            <TwitterIcon />
          </IconButton>
          <IconButton href="https://instagram.com" target="_blank" sx={{ color: '#555' }}>
            <InstagramIcon />
          </IconButton>
        </Grid>
      </Grid>

      {/* Copyright */}
      <Typography variant="body2" color="text.secondary" align="center" sx={{ mt: 3 }}>
        © 2025 ReUsa. Todos los derechos reservados.
      </Typography>
    </Box>
  );
}
