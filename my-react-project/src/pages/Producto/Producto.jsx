import { Box, Button, Grid, LinearProgress, Typography } from "@mui/material";
import { useCallback, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getProduct } from "../../backend/Product/Product";
import { toast } from "react-toastify";
import { ImageProduct } from "../../components/Products/ImageProduct";

export const Producto = () => {
  const { id } = useParams();
  const [producto, setProducto] = useState();
  const [loading, setLoading] = useState(true);

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

  useEffect(() => {
    fetchProduct();
  }, [fetchProduct]);

  return (
    <Box
      display={"flex"}
      justifyContent={"center"}
      alignItems={"center"}
      flexDirection={"column"}
      sx={{ width: "100%" }}
    >
      {loading ? (
        <Box>
          <LinearProgress />
        </Box>
      ) : (
        <Box
          p={5}
          display={"flex"}
          justifyContent={"center"}
          alignItems={"center"}
          flexDirection={"column"}
          sx={{ width: "100%" }}
        >
          <Typography variant="h4" gutterBottom>
            {producto.nombre}
          </Typography>

          <Grid
            container
            spacing={2}
            justifyContent="center"
            alignItems="center"
          >
            <Grid item width={"500px"}>
              <ImageProduct producto={producto} num={1} />
            </Grid>
            <Grid item width={"500px"}>
              <ImageProduct producto={producto} num={2} />
            </Grid>
          </Grid>
          <Typography>{producto.descripcion}</Typography>
          <Typography>{producto.precio}</Typography>
          <Typography>{producto.estado}</Typography>
          <Typography>{producto.categoria}</Typography>

          <Typography>{producto.subcategoria}</Typography>
          <Button color="primary">Comprar</Button>
        </Box>
      )}
    </Box>
  );
};
