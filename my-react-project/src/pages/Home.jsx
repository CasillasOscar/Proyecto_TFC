import { useEffect, useState } from "react";
import {
  Box,
  Typography,
  CardMedia,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogContentText,
  Button,
  Card,
  IconButton,
  Grid,
  CardContent,
  Tooltip,
} from "@mui/material";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import FavoriteIcon from "@mui/icons-material/Favorite";
import { toast } from "react-toastify";
import { ImageProduct } from "../components/Products/ImageProduct";
import { listProducts, saveFavorite } from "../backend/Product/product";
import EditIcon from "@mui/icons-material/Edit";
import { removeFavorite } from "../backend/User/User";

export default function Home({ user, favoritos, setFavoritos }) {
  const [productoSeleccionado, setProductoSeleccionado] = useState(null);
  const [productos, setProductos] = useState([]);

  useEffect(() => {
    fetchProductos();
  }, []);

  useEffect(() => {}, [productos]);

  const fetchProductos = async () => {
    try {
      const response = await listProducts();
      if (response.status === 200) {
        setProductos(response.data);
      } else {
        toast.error("Error al obtener los productos: " + response.data.message);
      }
    } catch (error) {
      toast.error("Error al obtener los productos");
      console.error("Error al obtener los productos:", error);
    }
  };

  const isFavorite = (productoId) => {
    return Array.isArray(favoritos) && favoritos.includes(productoId);
  };
  const handleToggleFavorite = async (productId) => {
    
    if (isFavorite(productId)) {
      try {
        const response = await removeFavorite(user.nickname, productId);
        if (response.status === 200) {
          toast.success("Favorito eliminado");
          const updatedFavorites = favoritos.filter(
            (favId) => favId !== productId
          );
          setFavoritos(updatedFavorites);
        } else {
          toast.error("Error al eliminar el favorito del servidor.");
        }
      } catch (error) {
        console.error("Error al eliminar favorito:", error);
        toast.error("Error de conexión al eliminar favorito.");
      }
    } else {
      try {
        const response = await saveFavorite(user.nickname, productId); 
        if (response.status === 200) {
          toast.success("Guardado en favoritos");
          const updatedFavorites = [...favoritos, productId];
          setFavoritos(updatedFavorites);
        } else {
          toast.error("Error al guardar en favoritos.");
        }
      } catch (error) {
        toast.error("Error al guardar en favoritos");
        console.error("Error al guardar en favoritos:", error);
      }
    }
  };

  const handleEditProduct = (productId) => {
    console.log("Acción: Editar producto", productId);
  };
  return (
    <Box
      sx={{
        p: 3,
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
      }}
    >
      <Typography variant="h5" gutterBottom textAlign="center">
        Comienza con nosotros a reutilizar productos y cuidar el medio ambiente
      </Typography>

      <Grid container spacing={2} justifyContent="center">
        {productos &&
          productos.map((producto) => (
            <Grid item xs={12} sm={6} md={4} lg={3} key={producto.id}>
              <Card sx={{ position: "relative", width: "200px" }}>
                {user && (
                  <IconButton
                    onClick={() => {
                      if (user.nickname !== producto.usuario) {
                        handleToggleFavorite(producto.id);
                      } else {
                        handleEditProduct(producto.id);
                      }
                    }}
                    sx={{
                      position: "absolute",
                      top: 8,
                      right: 8,
                      backgroundColor: "white",
                      "&:hover": { backgroundColor: "#eee" },
                    }}
                  >
                    {user && user.nickname !== producto.usuario ? (
                      <Tooltip title="Agregar a favoritos">
                        {isFavorite(producto.id) ? (
                          <FavoriteIcon color="error" />
                        ) : (
                          <FavoriteBorderIcon />
                        )}
                      </Tooltip>
                    ) : (
                      <Tooltip title="Editar producto">
                        <EditIcon />
                      </Tooltip>
                    )}
                  </IconButton>
                )}

                <ImageProduct producto={producto} />

                <CardContent>
                  <Typography variant="subtitle1">{producto.nombre}</Typography>
                  <Typography variant="body2" color="text.secondary">
                    Precio: {producto.precio} €
                  </Typography>
                  <Box sx={{ mt: 2, textAlign: "center" }}>
                    <Button
                      variant="outlined"
                      size="small"
                      onClick={() => setProductoSeleccionado(producto)}
                    >
                      🔍 Ver más
                    </Button>
                  </Box>
                </CardContent>
              </Card>
            </Grid>
          ))}
      </Grid>

      <Dialog
        open={Boolean(productoSeleccionado)}
        onClose={() => setProductoSeleccionado(null)}
        maxWidth="sm"
        fullWidth
      >
        {/* {productoSeleccionado && (
          <>
            <DialogTitle>{productoSeleccionado.nombre}</DialogTitle>
            <DialogContent>
              <CardMedia
                component="img"
                height="200"
                image={productoSeleccionado.imagen}
                alt={productoSeleccionado.nombre}
                sx={{ mb: 2 }}
              />
              <DialogContentText>
                <strong>Precio:</strong> {productoSeleccionado.precio}
              </DialogContentText>
              <DialogContentText sx={{ mt: 1 }}>
                <strong>Descripción:</strong> {productoSeleccionado.descripcion}
              </DialogContentText>
              <Box sx={{ mt: 3, textAlign: "right" }}>
                <Button
                  onClick={() => setProductoSeleccionado(null)}
                  variant="contained"
                >
                  Cerrar
                </Button>
              </Box>
            </DialogContent>
          </>
        )} */}
      </Dialog>
    </Box>
  );
}
