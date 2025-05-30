import { toast } from "react-toastify";
import { removeFavorite } from "../../backend/User/User";
import { saveFavorite } from "../../backend/Product/product";
import {
  Box,
  Typography,
  Button,
  Card,
  IconButton,
  Grid,
  CardContent,
  Tooltip,
} from "@mui/material";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import FavoriteIcon from "@mui/icons-material/Favorite";
import { ImageProduct } from "./ImageProduct";
import EditIcon from "@mui/icons-material/Edit";

export const ProductCard = ({ user, producto, favoritos, setFavoritos }) => {
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

  const handleProductSelect = (productId) =>{
    console.log(productId)
  }

  return (
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
              onClick={() => handleProductSelect(producto.id)}
            >
              🔍 Ver más
            </Button>
          </Box>
        </CardContent>
      </Card>
    </Grid>
  );
};
