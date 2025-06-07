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
import { getSales } from "../../backend/User/user";

export const UserVentasPopup = ({ isOpen, onClose, nickname}) => {
  const [ventas, setVentas] = useState([]);
  
    const fetchSales = useCallback(async () => {
      try {
        const response = await getSales(nickname);
        if (response.status === 200) {
          setVentas(response.data);
        } else {
          toast.error("Error al cargar las ventas");
        }
      } catch (error) {
        toast.error("Error al cargar las ventas");
        console.log("Error al cargar las ventas", error);
      }
    }, [nickname]);
  
    useEffect(() => {
      fetchSales();
    }, [fetchSales]);
  
    return (
      <Dialog
        open={isOpen}
        onClose={onClose}
        maxWidth="md"
        fullWidth
        sx={{ display: "flex", flexDirection: "column" }}
      >
        <DialogTitle sx={{ textAlign: "center" }}>
          Historial de ventas de <strong>{nickname}</strong>
        </DialogTitle>
        <DialogContent>
        {ventas && ventas.length > 0 ? (
          <TableContainer component={Paper}> 
            <Table sx={{ minWidth: 650 }} aria-label="tabla de compras">
              <TableHead>
                <TableRow>
                  {/* Encabezados de la tabla */}
                  <TableCell sx={{fontWeight:"bold", textAlign: "center"}}>Fecha de Venta</TableCell>
                  <TableCell sx={{fontWeight:"bold", textAlign: "center"}}>Producto</TableCell>
                  <TableCell sx={{fontWeight:"bold", textAlign: "center"}}>Precio (€)</TableCell>
                  <TableCell sx={{fontWeight:"bold", textAlign: "center"}}>Comprador</TableCell>
                  <TableCell sx={{fontWeight:"bold", textAlign: "center"}}>Email comprador</TableCell>
                  <TableCell sx={{fontWeight:"bold", textAlign: "center"}}>Teléfono comprador</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {ventas.map((venta) => (
                  <TableRow
                    key={venta.id} 
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                  >
                    {/* Celdas de datos para cada compra */}
                    <TableCell component="th" scope="row" sx={{ textAlign: "center"}}>
                      {venta.fechaVenta}
                    </TableCell>
                    <TableCell sx={{ textAlign: "center"}}>{venta.producto}</TableCell>
                    <TableCell sx={{ textAlign: "center"}}>{venta.precio} €</TableCell>
                    <TableCell sx={{ textAlign: "center"}}>{venta.usuario_comprador.nickname}</TableCell>
                    <TableCell sx={{ textAlign: "center"}}>{venta.usuario_comprador.email}</TableCell>
                    <TableCell sx={{ textAlign: "center"}}>{venta.usuario_comprador.telefono}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        ) : (
          <Box sx={{ p: 2, textAlign: 'center' }}>
            <Typography>No tienes ventas</Typography>
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
