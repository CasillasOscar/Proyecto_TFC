import {
  Button,
  Dialog,
  DialogActions,
  DialogTitle,
  DialogContent,
  Box,
  TextField,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  OutlinedInput,
} from "@mui/material";
import { toast } from "react-toastify";
import { getListProvincias, updateUser } from "../../backend/User/User";
import { useEffect, useState, useRef, useCallback } from "react";

export const UpdateUserPopup = ({ isOpen, onClose, onUserUpdated }) => {
  const localStorageUserString = localStorage.getItem("user");
  const originalUserObjectRef = useRef(null);

  const [provincias, setProvincias] = useState([]);

  const [nickname, setNickname] = useState("");
  const [nombre, setNombre] = useState("");
  const [apellido, setApellido] = useState("");
  const [telefono, setTelefono] = useState("");
  const [email, setEmail] = useState("");
  const [selectedProvincia, setSelectedProvincia] = useState("");

  const [disabledUpdated, setDisabledUpdated] = useState(true);
  const [changeFlags, setChangeFlags] = useState({
    nickname: false,
    nombre: false,
    apellido: false,
    telefono: false,
    provincia: false,
  });

  const fetchProvincias = useCallback(async () => {
    try {
      const response = await getListProvincias();
      if (response.status === 200) {
        const processedProvincias = response.data.provincias.map((p) => ({
          ...p,
          id: String(p.id),
        }));
        setProvincias(processedProvincias);
      } else {
        console.error(
          "Error al obtener las provincias:",
          response.data.message
        );
        toast.error("Error al obtener las provincias");
      }
    } catch (error) {
      toast.error("Error al obtener las provincias");
      console.error("Error al obtener las provincias:", error);
    }
  }, []);

  useEffect(() => {
    if (isOpen) {
      if (
        !originalUserObjectRef.current ||
        localStorageUserString !==
          originalUserObjectRef.current._localStorageString
      ) {
        const userParsed = localStorageUserString
          ? JSON.parse(localStorageUserString)
          : null;

        originalUserObjectRef.current = userParsed
          ? { ...userParsed, _localStorageString: localStorageUserString }
          : null;
      }

      const currentUser = originalUserObjectRef.current;

      fetchProvincias();

      if (currentUser) {
        setNickname(currentUser.nickname || "");
        setNombre(currentUser.nombre || "");
        setApellido(currentUser.apellido || "");
        setTelefono(currentUser.telefono || "");
        setEmail(currentUser.email || "");

        if (currentUser.provincia && currentUser.provincia !== "empty") {
          setSelectedProvincia(String(currentUser.provincia));
        } else {
          setSelectedProvincia("");
        }
      } else {
        setNickname("");
        setNombre("");
        setApellido("");
        setTelefono("");
        setEmail("");
        setSelectedProvincia("");
      }

      setChangeFlags({
        nickname: false,
        nombre: false,
        apellido: false,
        telefono: false,
        provincia: false,
      });
      setDisabledUpdated(true);
    }
  }, [isOpen, localStorageUserString, fetchProvincias]);

  useEffect(() => {
    const hasAnyChange = Object.values(changeFlags).some((flag) => flag);
    setDisabledUpdated(!hasAnyChange);
  }, [changeFlags]);

  const handleTextFieldChange = (fieldName, setter) => (event) => {
    const newValue = event.target.value;
    setter(newValue);

    const originalValue = originalUserObjectRef.current?.[fieldName] || "";
    setChangeFlags((prev) => ({
      ...prev,
      [fieldName]: newValue !== originalValue,
    }));
  };

  const handleProvinciaChange = (event) => {
    const newValue = event.target.value;
    setSelectedProvincia(newValue);

    const originalProvincia = originalUserObjectRef.current?.provincia || "";
    setChangeFlags((prev) => ({
      ...prev,
      provincia: String(newValue) !== String(originalProvincia),
    }));
  };

  const handleUserChange = async () => {
    try {
      const originalNickname = originalUserObjectRef.current?.nickname; 

      if (!originalNickname) {
        toast.error("No se pudo obtener el nickname original del usuario.");
        return;
      }
      let provinciaNombre = "";
      if (selectedProvincia) {
        const provinciaEncontrada = provincias.find(
          (p) => String(p.id) === String(selectedProvincia)
        );
        if (provinciaEncontrada) {
          provinciaNombre = provinciaEncontrada.nombre;
        } else {
          console.warn(
            "No se encontró el nombre para el ID de provincia:",
            selectedProvincia
          );
          toast.error("No se encontró el nombre de la provincia seleccionada.");
        }
      }

      const updatedUserData = {
        nickname,
        nombre,
        apellido,
        telefono,
        provincia: provinciaNombre, 
      };
      const response = await updateUser(originalNickname, updatedUserData);
        console.log("Response from updateUser:", response);
      if (response.status === 200) {
        toast.success("Usuario actualizado correctamente");
        onUserUpdated(response.data.user);
        onClose();
      } else {
        console.error("Error al actualizar el usuario:", response.data.message);
        toast.error("Error al actualizar el usuario");
      }
    } catch (error) {
      console.error("Error al actualizar el usuario:", error);
      toast.error("Error al actualizar el usuario");
    }
  };

  return (
    <Dialog
      open={isOpen}
      onClose={onClose}
      maxWidth="sm"
      fullWidth
      sx={{ display: "flex", flexDirection: "column" }}
    >
      <DialogTitle sx={{ textAlign: "center" }}>Actualizar usuario</DialogTitle>
      <DialogContent>
        <Box sx={{ display: "flex", flexDirection: "column", gap: 2, p: 2 }}>
          <TextField
            label="Nickname"
            value={nickname}
            onChange={handleTextFieldChange("nickname", setNickname)}
            fullWidth
          />
          <TextField
            label="Nombre"
            value={nombre}
            onChange={handleTextFieldChange("nombre", setNombre)}
            fullWidth
          />
          <TextField
            label="Apellido"
            value={apellido}
            onChange={handleTextFieldChange("apellido", setApellido)}
            fullWidth
          />
          <TextField label="Email" value={email} fullWidth disabled />
          <TextField
            label="Telefono"
            value={telefono}
            onChange={handleTextFieldChange("telefono", setTelefono)}
            fullWidth
          />
          <FormControl fullWidth>
            <InputLabel id="provincias-select-label">Provincias</InputLabel>
            <Select
              labelId="provincias-select-label"
              id="provincias-select"
              value={selectedProvincia}
              onChange={handleProvinciaChange}
              input={<OutlinedInput label="Provincias" />}
            >
              <MenuItem value="">
                <em>Selecciona una provincia</em>
              </MenuItem>
              {provincias.map((provincia) => (
                <MenuItem key={provincia.id} value={provincia.id}>
                  {provincia.nombre}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
        </Box>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="primary">
          Cancelar
        </Button>
        <Button
          onClick={handleUserChange}
          color="primary"
          disabled={disabledUpdated}
        >
          Actualizar
        </Button>
      </DialogActions>
    </Dialog>
  );
};
