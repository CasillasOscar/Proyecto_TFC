import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Drawer from '@mui/material/Drawer';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';

import MenuIcon from '@mui/icons-material/Menu';
import FavoriteIcon from '@mui/icons-material/Favorite';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import LoginIcon from '@mui/icons-material/Login';
import AddCircleIcon from '@mui/icons-material/AddCircle';

export default function Header() {
  const [openDrawer, setOpenDrawer] = useState(false);
  const navigate = useNavigate();

  const toggleDrawer = (open) => () => {
    setOpenDrawer(open);
  };

  return (
    <>
      <AppBar position="sticky" sx={{ backgroundColor: '#ffffff', color: 'black', boxShadow: 2 }}>
        <Toolbar sx={{ justifyContent: 'space-between' }}>
          <IconButton edge="start" onClick={toggleDrawer(true)}>
            <MenuIcon />
          </IconButton>

          <Typography
            variant="h6"
            component="div"
            onClick={() => navigate('/')}
            sx={{ cursor: 'pointer', textAlign: 'center', flexGrow: 1 }}
          >
            ReUsa
          </Typography>

          <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
            <IconButton onClick={() => navigate('/favoritos')} title="Favoritos">
              <FavoriteIcon color="error" />
            </IconButton>

            <IconButton onClick={() => navigate('/perfil')} title="Perfil">
              <AccountCircleIcon />
            </IconButton>

            <Button
              variant="outlined"
              startIcon={<LoginIcon />}
              onClick={() => navigate('/login')}
            >
              Iniciar sesión
            </Button>

            <Button
              variant="contained"
              color="success"
              startIcon={<AddCircleIcon />}
              onClick={() => navigate('/nuevo')}
            >
              Vender
            </Button>
          </Box>
        </Toolbar>
      </AppBar>

      <Drawer anchor="left" open={openDrawer} onClose={toggleDrawer(false)}>
        <Box sx={{ width: 250 }} role="presentation" onClick={toggleDrawer(false)}>
          <List>
            <ListItem button onClick={() => navigate('/')}> <ListItemText primary="Inicio" /> </ListItem>
            <ListItem button onClick={() => navigate('/categorias')}> <ListItemText primary="Categorías" /> </ListItem>
            <ListItem button onClick={() => navigate('/mensajes')}> <ListItemText primary="Mensajes" /> </ListItem>
            <ListItem button onClick={() => navigate('/configuracion')}> <ListItemText primary="Configuración" /> </ListItem>
          </List>
        </Box>
      </Drawer>
    </>
  );
}
