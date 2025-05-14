import React from 'react';
import { Container, Typography, Box, Grid, Avatar, Paper } from '@mui/material';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import L from 'leaflet';

const icon = L.icon({
  iconUrl: 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon.png',
  iconSize: [25, 41],
  iconAnchor: [12, 41],
});

const equipo = [
  {
    nombre: 'Christian Mengibar',
    rol: 'Fundador & CEO',
    imagen: 'src/assets/chr.jpeg',
    ubicacion: [40.327, -3.763],
    ciudad: 'Leganés, Madrid',
  },
  {
    nombre: 'Óscar Casillas',
    rol: 'Fundador & CEO',
    imagen: 'src/assets/osc.jpg',
    ubicacion: [40.163, -3.872],
    ciudad: 'Ugena, Toledo',
  },
];

export default function AcercaNosotros() {
  return (
    <Container sx={{ py: 5 }}>
      <Typography variant="h4" gutterBottom textAlign="center">
        🌱 Acerca de ReUsa
      </Typography>

      <Paper elevation={3} sx={{ p: 3, mb: 4, backgroundColor: '#f9f9f9', textAlign: 'center' }}>
        <Typography variant="h6" gutterBottom>
          Nuestra misión
        </Typography>
        <Typography variant="body2" sx={{ maxWidth: 700, mx: 'auto' }}>
          En ReUsa, nuestra misión es transformar la manera en que las personas consumen y reutilizan productos.
          Queremos construir una comunidad activa y responsable que priorice la sostenibilidad, ofreciendo una
          plataforma accesible, segura y eficiente para prolongar la vida útil de los objetos. Apostamos por una
          economía circular donde cada artículo tenga más de una historia y contribuya a un planeta más limpio
          y justo para todos.
        </Typography>
      </Paper>

      <Paper elevation={3} sx={{ p: 3, mb: 4, textAlign: 'center' }}>
        <Typography variant="h6" gutterBottom>
          Nuestros valores
        </Typography>
        <Box component="ul" sx={{ listStyle: 'none', p: 0, m: 0 }}>
          <li>
            <Typography variant="body2">🌍 Sostenibilidad: Reducimos el impacto ambiental fomentando la reutilización.</Typography>
          </li>
          <li>
            <Typography variant="body2">🔒 Confianza: Creamos un entorno seguro y transparente para usuarios.</Typography>
          </li>
          <li>
            <Typography variant="body2">👥 Inclusión: Accesible para todas las personas, sin importar su experiencia digital.</Typography>
          </li>
          <li>
            <Typography variant="body2">💡 Innovación: Buscamos constantemente mejorar nuestra plataforma y servicios.</Typography>
          </li>
        </Box>
      </Paper>

      <Box sx={{ mt: 5 }}>
        <Typography variant="h5" gutterBottom textAlign="center">
          Conoce al equipo
        </Typography>
        <Grid container spacing={4} justifyContent="center">
          {equipo.map((persona, idx) => (
            <Grid item xs={12} sm={4} key={idx}>
              <Box sx={{ textAlign: 'center' }}>
                <Avatar
                  src={persona.imagen}
                  alt={persona.nombre}
                  sx={{ width: 100, height: 100, margin: '0 auto', mb: 1 }}
                />
                <Typography variant="subtitle1">{persona.nombre}</Typography>
                <Typography variant="body2" color="text.secondary">{persona.rol}</Typography>

                {/* Mapa */}
                <Box sx={{ width: '100%', height: 200, mt: 2, borderRadius: 2, overflow: 'hidden' }}>
                  <MapContainer center={persona.ubicacion} zoom={13} style={{ height: '100%', width: '100%' }}>
                    <TileLayer
                      attribution='&copy; <a href="https://osm.org/copyright">OpenStreetMap</a>'
                      url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                    />
                    <Marker position={persona.ubicacion} icon={icon}>
                      <Popup>{persona.ciudad}</Popup>
                    </Marker>
                  </MapContainer>
                </Box>
              </Box>
            </Grid>
          ))}
        </Grid>
      </Box>
    </Container>
  );
}
