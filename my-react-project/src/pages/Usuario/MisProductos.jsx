import { Box, Grid, Typography } from "@mui/material";
import { ProductCard } from "../../components/Products/ProductCard";
import { useCallback, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { listProductsUser } from "../../backend/Product/Product";
import { toast } from "react-toastify";

export const MisProductos = ({user}) => {
  const [productos, setProductos] = useState([]);
  const { nickname } = useParams();

  const fetchProductosUser = useCallback(async () => {
    try {
      const response = await listProductsUser(nickname);
      if (response.status === 200) {
        if (response.data.length > 0) {
          setProductos(response.data);
        } else {
          setProductos([]);
        }
      } else {
        console.log("Error al cargar los favoritos");
        toast.error("Ha habido un problema en la carga de tus productos");
      }
    } catch (error) {
      toast.error("Ha habido un problema en la carga de tus productos");
      console.log("Error al cargar los favoritos", error);
    }
  }, [nickname]);

  useEffect(() => {
    fetchProductosUser();
  }, [fetchProductosUser]);

  useEffect(() => {
    console.log(productos);
  }, [productos]);

  return (
    <Box sx={{ p: 4 }}>
      <Typography variant="h4" gutterBottom textAlign="center">
        Mis productos
      </Typography>

      {productos.length < 1 && (
        <Typography textAlign="center" sx={{ mt: 4 }} color="text.secondary">
          No tienes productos activos.
        </Typography>
      )}

      <Grid container spacing={2} justifyContent="center">
        {productos &&
          productos.map((producto) => <ProductCard producto={producto} user={user}/>)}
      </Grid>
    </Box>
  );
};
