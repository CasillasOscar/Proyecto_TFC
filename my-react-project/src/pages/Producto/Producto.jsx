import {
  Box,
  Button,
  Grid,
  LinearProgress,
  Typography,
  Paper,
  Divider,
} from "@mui/material";
import { useCallback, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
  buyProduct,
  deleteProduct,
  getProduct,
} from "../../backend/Product/Product";
import { toast } from "react-toastify";
import { ImageProductDetail } from "../../components/Products/ImageProductDetail";
import { UpdateProductPopup } from "../../components/Popups/UpdateProductPopup";

export const Producto = ({ user }) => {
  const { id } = useParams();
  const [producto, setProducto] = useState();
  const [loading, setLoading] = useState(true);
  const [editProductIsOpen, setEditProductIsOpen] = useState(false);
  const navigate = useNavigate();

  const fetchProduct = useCallback(async () => {
    try {
      const response = await getProduct(Number(id));
      if (response.status === 200) {
        setProducto(response.data.product);
        setLoading(false);
      } else {
        toast.error("Error al cargar el producto");
      }
    } catch (error) {
      toast.error("Error al cargar el producto");
      console.error("Error al cargar el producto:", error);
    }
  }, [id]);

  const handleDeleteProduct = async () => {
    try {
      const response = await deleteProduct(id);
      if (response.status == 200) {
        toast.success("Producto eliminado");
        fetchProduct();
      } else {
        toast.error("Fallo al eliminar el producto");
      }
    } catch (error) {
      toast.error("Fallo al eliminar el producto");
      console.error("Fallo al eliminar el producto", error);
    }
  };

  useEffect(() => {
    fetchProduct();
  }, [fetchProduct]);

  const handleBuyProduct = async () => {
    try {
      const response = await buyProduct(Number(producto.id), user.nickname);
      if (response.status === 200) {
        toast.success(
          "Enorabuena! revisa en tu perfil tu compras y ponte en contacto con el vendedor"
        );
        navigate("/perfil");
      } else {
        toast.error("Algo ha salido mal en la compra del producto");
      }
    } catch (error) {
      toast.error("Algo ha salido mal en la compra del producto");
      console.log("Error al comprar el producto", error);
    }
  };

  return (
    <Box
      display="flex"
      justifyContent="center"
      alignItems="center"
      flexDirection="column"
      sx={{
        width: "100%",
        minHeight: "100vh",
        backgroundColor: "#f0f2f5",
        py: 3,
      }}
    >
      {loading ? (
        <Box sx={{ width: "80%", maxWidth: 800 }}>
          <LinearProgress />
        </Box>
      ) : (
        <Paper
          elevation={6}
          sx={{
            p: 4,
            width: "90%",
            maxWidth: 1000,
            backgroundColor: "#ffffff",
            borderRadius: 6,
          }}
        >
          <Typography
            variant="h3"
            gutterBottom
            sx={{
              fontWeight: "bold",
              textAlign: "center",
              color: "#333",
              mb: 2,
            }}
          >
            {producto.nombre}
          </Typography>

          <Divider sx={{ my: 2 }} />

          <Grid container spacing={3} justifyContent="center">
            {[1, 2].map((num) => (
              <Grid item xs={12} md={6} key={num}>
                <Paper
                  elevation={3}
                  sx={{
                    overflow: "hidden",
                    borderRadius: 4,
                    height: "350px",
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                    transition: "transform 0.3s, box-shadow 0.3s",
                    "&:hover": {
                      transform: "scale(1.02)",
                      boxShadow: "0 8px 20px rgba(0,0,0,0.15)",
                    },
                  }}
                >
                  <ImageProductDetail producto={producto} num={num} />
                </Paper>
              </Grid>
            ))}
          </Grid>

          <Box sx={{ mt: 3 }}>
            <Typography
              variant="h6"
              gutterBottom
              sx={{ color: "#555", fontWeight: "bold" }}
            >
              Descripción
            </Typography>
            <Typography variant="body1" sx={{ mb: 2, color: "#666" }}>
              {producto.descripcion}
            </Typography>

            <Grid container spacing={2}>
              <Grid item xs={12} sm={6}>
                <Typography
                  variant="subtitle1"
                  sx={{ fontWeight: "bold", color: "#444" }}
                >
                  Precio:
                </Typography>
                <Typography
                  variant="body1"
                  sx={{ color: "#2e7d32", fontSize: "1.1rem" }}
                >
                  ${producto.precio}
                </Typography>
              </Grid>

              <Grid item xs={12} sm={6}>
                <Typography
                  variant="subtitle1"
                  sx={{ fontWeight: "bold", color: "#444" }}
                >
                  Estado:
                </Typography>
                <Typography variant="body1" sx={{ color: "#666" }}>
                  {producto.estado}
                </Typography>
              </Grid>

              <Grid item xs={12} sm={6}>
                <Typography
                  variant="subtitle1"
                  sx={{ fontWeight: "bold", color: "#444" }}
                >
                  Categoría:
                </Typography>
                <Typography variant="body1" sx={{ color: "#666" }}>
                  {producto.categoria}
                </Typography>
              </Grid>

              <Grid item xs={12} sm={6}>
                <Typography
                  variant="subtitle1"
                  sx={{ fontWeight: "bold", color: "#444" }}
                >
                  Subcategoría:
                </Typography>
                <Typography variant="body1" sx={{ color: "#666" }}>
                  {producto.subcategoria}
                </Typography>
              </Grid>
            </Grid>

            <Box display="flex" justifyContent="center" mt={4}>
              {user && user.nickname !== producto.vendedor && (
                <Button
                  variant="contained"
                  color="success"
                  size="large"
                  sx={{ px: 5 }}
                  onClick={() => handleBuyProduct()}
                >
                  Comprar
                </Button>
              )}
              {user && user.nickname === producto.vendedor && (
                <Box
                  sx={{
                    display: "flex",
                    gap: 5,
                  }}
                >
                  <Button
                    variant="contained"
                    color="success"
                    size="large"
                    sx={{ px: 5 }}
                    onClick={() => setEditProductIsOpen(true)}
                  >
                    Editar
                  </Button>
                  <Button
                    variant="outlined"
                    color="error"
                    size="large"
                    sx={{ px: 5 }}
                    onClick={() => handleDeleteProduct()}
                  >
                    Eliminar
                  </Button>
                </Box>
              )}
            </Box>
          </Box>
        </Paper>
      )}
      {editProductIsOpen && (
        <UpdateProductPopup
          idProduct={producto.id}
          isOpen={editProductIsOpen}
          onCancel={() => setEditProductIsOpen(false)}
          fetchProductos={() => fetchProduct()}
        />
      )}
    </Box>
  );
};
