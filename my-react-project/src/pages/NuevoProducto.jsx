import { useCallback, useEffect, useState } from "react";
import {
  Box,
  Typography,
  TextField,
  Button,
  Input,
  FormControl,
  Select,
  MenuItem,
  InputLabel,
  OutlinedInput,
} from "@mui/material";
import { getSubcategories, userSellProduct } from "../backend/Product/Product";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

export default function NuevoProducto() {
  const navigate = useNavigate();
  const [imagen, setImagen] = useState(null);
  const [imagen2, setImagen2] = useState(null);
  const [preview, setPreview] = useState(null);
  const [preview2, setPreview2] = useState(null);
  const [disabledUpdated, setDisabledUpdated] = useState(true);
  const [changeFlags, setChangeFlags] = useState({
    nombre: false,
    descripcion: false,
    precio: false,
    imagen1: false,
    imagen2: false,
    subcategoria: false,
    estado: false,
  });
  const estadoProducto = {
    nuevo: "Nuevo",
    usado: "Seminuevo",
    aceptable: "Aceptable",
    deteriorado: "Deteriorado",
  };
  const [selectedEstado, setSelectedEstado] = useState("");
  const [nombre, setNombre] = useState("");
  const [descripcion, setDescripcion] = useState("");
  const [precio, setPrecio] = useState();
  const [selectedSubcategoria, setSelectedSubcategoria] = useState("");
  const [subcategorias, setSubcategorias] = useState([]);

  useEffect(() => {
    const hasAnyChange = Object.values(changeFlags).every((flag) => flag);
    setDisabledUpdated(!hasAnyChange);
  }, [changeFlags]);

  const fetchSubcategorias = useCallback(async () => {
    try {
      const response = await getSubcategories();
      if (response.status === 200) {
        setSubcategorias(response.data.subcategorias);
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
    fetchSubcategorias();
  }, [fetchSubcategorias]);

  const handleImagenChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setImagen(file);
      setPreview(URL.createObjectURL(file));
      setChangeFlags((prev) => ({
        ...prev,
        imagen1: true,
      }));
    }
  };

  const handleImagenChange2 = (e) => {
    const file = e.target.files[0];
    if (file) {
      setImagen2(file);
      setPreview2(URL.createObjectURL(file));
      setChangeFlags((prev) => ({
        ...prev,
        imagen2: true,
      }));
    }
  };

  const handleSubcategoriaChange = (event) => {
    const newValue = event.target.value;
    setSelectedSubcategoria(newValue);
    setChangeFlags((prev) => ({
      ...prev,
      subcategoria: newValue ? true : false,
    }));
  };

  const handleEstadoChange = (event) => {
    const newValue = event.target.value;
    setSelectedEstado(newValue);
    setChangeFlags((prev) => ({
      ...prev,
      estado: newValue ? true : false,
    }));
  };

  const handleTextFieldChange = (event, fieldName) => {
    const newValue = event.target.value;
    if (!newValue) {
      setChangeFlags((prev) => ({
        ...prev,
        [fieldName]: false,
      }));
      return;
    }

    setChangeFlags((prev) => ({
      ...prev,
      [fieldName]: true,
    }));

    switch (fieldName) {
      case "nombre":
        setNombre(newValue);
        break;
      case "descripcion":
        setDescripcion(newValue);
        break;
      case "precio":
        setPrecio(parseFloat(newValue));
        break;
      default:
        break;
    }
  };

  const clearForm = () => {
    setImagen(null);
    setImagen2(null);
    setPreview(null);
    setPreview2(null);
    setSelectedSubcategoria("");
    setSelectedEstado("");
    setChangeFlags({
      nombre: false,
      descripcion: false,
      precio: false,
      imagen1: false,
      imagen2: false,
      subcategoria: false,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const userString = localStorage.getItem("user");
    const userObject = JSON.parse(userString);
    const nickname = userObject.nickname;

    const data = {
      nombre: nombre,
      descripcion: descripcion,
      estado: selectedEstado,
      subcategoria: selectedSubcategoria,
      precio: precio,
    };
    console.log("Data to send:", data);

    const response = await userSellProduct(nickname, imagen, imagen2, data);
    try {
      if (response.status === 200) {
        toast.success("Producto publicado correctamente");
        clearForm();
        navigate("/")
      } else {
        toast.error("Error al publicar el producto");
      }
    } catch (error) {
      console.error("Error al publicar el producto:", error);
      toast.error("Error al publicar el producto");
    }
  };

  return (
    <Box
      sx={{
        p: 3,
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <Box
        component="form"
        onSubmit={handleSubmit}
        sx={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          gap: 2,
          maxWidth: 500,
        }}
      >
        <Typography variant="h5" mb={2} sx={{ textAlign: "center" }}>
          📦 Añadir nuevo producto
        </Typography>

        <TextField
          label="Nombre del producto"
          required
          fullWidth
          value={nombre}
          onChange={(e) => handleTextFieldChange(e, "nombre")}
        />
        <TextField
          label="Precio (€)"
          type="number"
          required
          fullWidth
          value={precio}
          onChange={(e) => handleTextFieldChange(e, "precio")}
          inputProps={{
            step: "0.01",
          }}
        />
        <TextField
          label="Descripción"
          multiline
          rows={4}
          required
          fullWidth
          value={descripcion}
          onChange={(e) => handleTextFieldChange(e, "descripcion")}
        />

        <FormControl fullWidth>
          <InputLabel id="subcategorias-select-label">Subcategorías</InputLabel>
          <Select
            labelId="subcategorias-select-label"
            id="subcategorias-select"
            value={selectedSubcategoria}
            onChange={handleSubcategoriaChange}
            input={<OutlinedInput label="Subcategorias" />}
          >
            <MenuItem value="">
              <em>Selecciona una subcategoría</em>
            </MenuItem>
            {subcategorias.map((subcategoria) => (
              <MenuItem key={subcategoria.id} value={subcategoria.id}>
                {subcategoria.nombre}
              </MenuItem>
            ))}
          </Select>
        </FormControl>

        <FormControl fullWidth>
          <InputLabel id="estado-select-label">Estado del producto</InputLabel>
          <Select
            labelId="estado-select-label"
            id="estado-select"
            value={selectedEstado}
            onChange={handleEstadoChange}
            input={<OutlinedInput label="Estado del producto" />}
          >
            <MenuItem value="">
              <em>Selecciona el estado de tu producto</em>
            </MenuItem>
            {Object.values(estadoProducto).map((estadoValor) => (
              <MenuItem key={estadoValor} value={estadoValor}>
                {estadoValor}
              </MenuItem>
            ))}
          </Select>
        </FormControl>

        <Input
          type="file"
          onChange={handleImagenChange}
          multiple
          accept="image/jpg, image/jpeg, image/png"
        />

        <Input
          type="file"
          onChange={handleImagenChange2}
          multiple
          accept="image/jpg, image/jpeg, image/png"
        />

        {preview && (
          <Box mt={2}>
            <Typography variant="subtitle2">Vista previa:</Typography>
            <Box sx={{ display: "flex", gap: 2, mt: 1 }}>
              <img
                src={preview}
                alt="preview"
                style={{ width: "200px", objectFit: "cover" }}
              />
              <img
                src={preview2}
                alt="preview"
                style={{ width: "200px", objectFit: "cover" }}
              />
            </Box>
          </Box>
        )}

        <Button
          variant="contained"
          type="submit"
          color="success"
          fullWidth
          disabled={disabledUpdated}
        >
          Publicar
        </Button>
      </Box>
    </Box>
  );
}
