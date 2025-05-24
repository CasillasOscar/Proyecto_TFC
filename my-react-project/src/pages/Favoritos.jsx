
import {
  Box, Grid, Card, CardMedia, CardContent, Typography, IconButton
} from '@mui/material';
import FavoriteIcon from '@mui/icons-material/Favorite';



export default function Favoritos() {



  return (
    <Box sx={{ p: 4 }}>
      <Typography variant="h4" gutterBottom textAlign="center">
        ❤️ Productos guardados
      </Typography>

        <Typography textAlign="center" sx={{ mt: 4 }} color="text.secondary">
          No has guardado ningún producto como favorito todavía.
        </Typography>
      
        {/* <Grid container spacing={3} justifyContent="center">
        
            <Grid item xs={12} sm={6} md={4} lg={3} key={'3'}>
              <Card sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
                <CardMedia component="img" height="200" image={} alt={'loro'} />
                <CardContent sx={{ flexGrow: 1 }}>
                  <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Typography variant="h6">Nombre</Typography>
                    <IconButton color="error" onClick={() => {}}>
                      <FavoriteIcon />
                    </IconButton>
                  </Box>
                  <Typography variant="body2" color="text.secondary">
                    Precio
                  </Typography>
                </CardContent>
              </Card>
            </Grid>
         
        </Grid> */}
      
    </Box>
  );
}
