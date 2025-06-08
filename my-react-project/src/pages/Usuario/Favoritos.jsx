import { Box, Typography, Grid } from "@mui/material";
import { listProductsFavorites } from "../../backend/User/user";
import { useCallback, useEffect, useState } from "react";
import { ProductCard } from "../../components/Products/ProductCard";

export default function Favoritos({ user, favoritos, setFavoritos }) {
  const [listFavorites, setListFavoritos] = useState([]);

  const fetchFavoritos = useCallback(async () => {
    try {
      const response = await listProductsFavorites(user.nickname);
      if (response.status === 200) {
        if (response.data.favorites_products.length > 0) {
          setListFavoritos(response.data.favorites_products);
        } else {
          setListFavoritos([]);
        }
      } else {
        console.log("Error al cargar los favoritos");
      }
    } catch (error) {
      console.log("Error al cargar los favoritos", error);
    }
  }, [user]);

  useEffect(() => {
    fetchFavoritos();
  }, [fetchFavoritos, favoritos]);

  return (
    <Box sx={{ p: 4 }}>
      <Typography variant="h4" gutterBottom textAlign="center">
        ❤️ Favoritos
      </Typography>

      {listFavorites.length < 1 && (
        <Typography textAlign="center" sx={{ mt: 4 }} color="text.secondary">
          No has guardado ningún producto como favorito todavía o el vendedor ha eliminado el producto.
        </Typography>
      )}

      <Grid container spacing={2} justifyContent="center">
        {listFavorites &&
          listFavorites.map((producto) => (
            <ProductCard
              producto={producto}
              user={user}
              favoritos={favoritos}
              setFavoritos={setFavoritos}
            />
          ))}
      </Grid>
    </Box>
  );
}
