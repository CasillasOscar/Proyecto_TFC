import { useState } from "react";
import {
  TextField,
  Button,
  Box,
  Typography,
  Paper,
  Stack,
} from "@mui/material";
import { useNavigate } from "react-router-dom";
import { login } from "../../backend/Auth/Auth";
import { toast } from "react-toastify";

export default function LoginPage({handleUserChange}) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await login(email, password);
      if (response && response.status === 200) {
        const { token, refreshToken, user } = response.data;
        saveInfoInLocalStorage(token, refreshToken, user);
        handleUserChange()
        toast.success(`Bienvenido ${user.nickname}!`);
        navigate("/");
      } else if (response.status === 400) {
        toast.error(response.data.error);
      } else {
        toast.error("Error al iniciar sesión. Por favor, inténtalo de nuevo más tarde.");
        console.error("Respuesta inesperada del servidor:", response);
      }
    } catch (error) {
      console.error("Error durante el inicio de sesión (red/conexión):", error);
      toast.error("Error al iniciar sesión. Por favor, inténtalo de nuevo más tarde.");
    }
  };

  const saveInfoInLocalStorage = (token, refreshToken, user) => {
    localStorage.setItem("token", token);
    localStorage.setItem("refreshToken", refreshToken); 
    localStorage.setItem("user", JSON.stringify(user));
  }

  return (
    <Box
      sx={{
        minHeight: "60vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <Paper elevation={6} sx={{ padding: 4, width: "100%", maxWidth: 400 }}>
        <Typography variant="h5" mb={3} textAlign="center">
          Iniciar Sesión
        </Typography>
        <form onSubmit={handleLogin}>
          <Stack spacing={2}>
            <TextField
              label="Correo electrónico"
              type="email"
              fullWidth
              required
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            <TextField
              label="Contraseña"
              type="password"
              fullWidth
              required
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />

            <Button type="submit" variant="contained" color="primary" fullWidth>
              Entrar
            </Button>

            <Button
              variant="text"
              color="error"
              fullWidth
              onClick={() => navigate("/recuperar")}
            >
              ¿Has olvidado tu contraseña?
            </Button>

            <Button
              variant="outlined"
              color="secondary"
              fullWidth
              onClick={() => navigate("/register")}
            >
              ¿No tienes cuenta? Regístrate
            </Button>
          </Stack>
        </form>
      </Paper>
    </Box>
  );
}
