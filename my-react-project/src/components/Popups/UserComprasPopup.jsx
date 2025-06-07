import {
  Button,
  Dialog,
  DialogActions,
  DialogTitle,
  DialogContent,
  Typography,
  Box,
  TableContainer,
  Paper,
  Table,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
} from "@mui/material";
import { useCallback, useEffect, useState } from "react";
import { toast } from "react-toastify";
import { getPurchases } from "../../backend/User/user";

export const UserComprasPopup = ({ isOpen, onClose, nickname }) => {
  const [compras, setCompras] = useState([]);

  const fetchPurchases = useCallback(async () => {
    try {
      const response = await getPurchases(nickname);
      if (response.status === 200) {
        setCompras(response.data);
      } else {
        toast.error("Error al cargar las compras");
      }
    } catch (error) {
      toast.error("Error al cargar las compras");
      console.log("Error al cargar las compras", error);
    }
  }, [nickname]);

  useEffect(() => {
    fetchPurchases();
  }, [fetchPurchases]);

  return (
    <Dialog
      open={isOpen}
      onClose={onClose}
      maxWidth="md"
      fullWidth
      sx={{ display: "flex", flexDirection: "column" }}
    >
      <DialogTitle sx={{ textAlign: "center" }}>
        Historial de compras de <strong>{nickname}</strong>
      </DialogTitle>
      <DialogContent>
      {compras && compras.length > 0 ? (
        <TableContainer component={Paper}> 
          <Table sx={{ minWidth: 650 }} aria-label="tabla de compras">
            <TableHead>
              <TableRow>
                {/* Encabezados de la tabla */}
                <TableCell sx={{fontWeight:"bold", textAlign: "center"}}>Fecha de Venta</TableCell>
                <TableCell sx={{fontWeight:"bold", textAlign: "center"}}>Producto</TableCell>
                <TableCell sx={{fontWeight:"bold", textAlign: "center"}}>Precio (€)</TableCell>
                <TableCell sx={{fontWeight:"bold", textAlign: "center"}}>Vendedor</TableCell>
                <TableCell sx={{fontWeight:"bold", textAlign: "center"}}>Email vendedor</TableCell>
                <TableCell sx={{fontWeight:"bold", textAlign: "center"}}>Teléfono vendedor</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {compras.map((compra) => (
                <TableRow
                  key={compra.id} 
                  sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                >
                  {/* Celdas de datos para cada compra */}
                  <TableCell component="th" scope="row" sx={{ textAlign: "center"}}>
                    {compra.fechaVenta}
                  </TableCell>
                  <TableCell sx={{ textAlign: "center"}}>{compra.producto}</TableCell>
                  <TableCell sx={{ textAlign: "center"}}>{compra.precio} €</TableCell>
                  <TableCell sx={{ textAlign: "center"}}>{compra.usuario_vendedor.nickname}</TableCell>
                  <TableCell sx={{ textAlign: "center"}}>{compra.usuario_vendedor.email}</TableCell>
                  <TableCell sx={{ textAlign: "center"}}>{compra.usuario_vendedor.telefono}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      ) : (
        <Box sx={{ p: 2, textAlign: 'center' }}>
          <Typography>No tienes compras</Typography>
        </Box>
      )}
    </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="error" variant="outlined">
          Salir
        </Button>
      </DialogActions>
    </Dialog>
  );
};
