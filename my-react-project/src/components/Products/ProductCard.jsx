import { toast } from "react-toastify";
import { removeFavorite } from "../../backend/User/user";
import { deleteProduct, saveFavorite } from "../../backend/Product/Product";
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
import { limitText } from "../../utils/textUtils";
import { useState } from "react";
import { UpdateProductPopup } from "../Popups/UpdateProductPopup";
import DeleteIcon from '@mui/icons-material/Delete';
import { useNavigate } from "react-router-dom";

export const ProductCard = ({ user, producto, favoritos, setFavoritos, fetchProductos }) => {
  const [editProductIsOpen, setEditProductIsOpen] = useState(false);
  const [idProductEdit, setProductEdit] = useState();
  const navigate = useNavigate()

  const ensureIdIsNumber = (productoId) => {
    let idToCompare = productoId;
    if (typeof productoId === "string") {
      idToCompare = Number(productoId);
    }
    return idToCompare;
  };
  const isFavorite = (productoId) => {
    return (
      Array.isArray(favoritos) &&
      favoritos.includes(ensureIdIsNumber(productoId))
    );
  };
  const handleToggleFavorite = async (productoId) => {
    if (isFavorite(productoId)) {
      try {
        const response = await removeFavorite(user.nickname, productoId);
        if (response.status === 200) {
          toast.success("Favorito eliminado");
          const updatedFavorites = favoritos.filter(
            (favId) => favId !== ensureIdIsNumber(productoId)
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
        const response = await saveFavorite(user.nickname, productoId);
        if (response.status === 200) {
          toast.success("Guardado en favoritos");
          const updatedFavorites = [...favoritos, productoId];
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
    setProductEdit(productId);
    setEditProductIsOpen(true);
  };

  const handleProductSelect = (productId) => {
    navigate(`/producto/${productId}`);
  };

  const handleDeleteProduct = async (productId) => {
    try{
      console.log(productId)
      const response = await deleteProduct(productId)
      if(response.status == 200){
        toast.success("Producto eliminado")
        fetchProductos()
      } else {
        toast.error("Fallo al eliminar el producto")
      }
    } catch (error){
      toast.error("Fallo al eliminar el producto")
      console.error("Fallo al eliminar el producto", error)
    }
  }

  return (
    <Grid item key={producto.id}>
      <Card sx={{ position: "relative", width: "200px"}}>
        {user && (
          <>
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

            {user.nickname == producto.usuario && (
              <IconButton sx={{
                position: "absolute",
                top: 8,
                right: 60,
                backgroundColor: "white",
                "&:hover": { backgroundColor: "#eee" },
              }}
               onClick={()=>handleDeleteProduct(producto.id)}>
              <Tooltip title="Eliminar producto">
                <DeleteIcon />
              </Tooltip>
            </IconButton>)}
          </>
        )}

        <ImageProduct producto={producto} num={1} />

        <CardContent>
          <Typography variant="subtitle1">
            {limitText(producto.nombre, 13)}
          </Typography>
          <Typography variant="body2" color="text.secondary">
            {limitText(String(producto.precio), 4)} €
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
      {editProductIsOpen && (
        <UpdateProductPopup
          idProduct={idProductEdit}
          isOpen={editProductIsOpen}
          onCancel={() => setEditProductIsOpen(false)}
          fetchProductos={()=> fetchProductos()}
        />
      )}
    </Grid>
  );
};
