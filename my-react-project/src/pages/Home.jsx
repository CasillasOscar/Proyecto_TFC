import { useEffect, useState } from "react";
import {
  Box,
  Typography,
  Grid,
} from "@mui/material";
import { toast } from "react-toastify";
import { listProducts } from "../backend/Product/Product";
import { ProductCard } from "../components/Products/ProductCard";

export default function Home({ user, favoritos, setFavoritos }) {
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


  return (
    <Box
      sx={{
        p: 3,
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
      }}
    >
      <Typography variant="h5" gutterBottom textAlign="center" mb={3}>
        Comienza con nosotros a reutilizar productos y cuidar el medio ambiente
      </Typography>

      <Grid container spacing={2} justifyContent="center" gap={3}>
        {productos &&
          productos.map((producto) => (
            <ProductCard producto={producto} user={user} favoritos={favoritos} setFavoritos={setFavoritos} fetchProductos={fetchProductos}/>
          ))}
      </Grid>
    </Box>
  );
}
