import {
  Button,
  Dialog,
  DialogActions,
  DialogTitle,
  DialogContent,
} from "@mui/material";


export const UserComprasPopup = ({ isOpen, onClose, user}) => {
  
  return (
    <Dialog
      open={isOpen}
      onClose={onClose}
      maxWidth="sm"
      fullWidth
      sx={{ display: "flex", flexDirection: "column" }}
    >
      <DialogTitle sx={{ textAlign: "center" }}>Estas son tus compras {user.nickname}</DialogTitle>
      <DialogContent>
        
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="error" variant="outlined">
          Salir
        </Button>
      </DialogActions>
    </Dialog>
  );
};
