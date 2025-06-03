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
import {
  getProduct,
  getSubcategories,
  updateProduct,
} from "../../backend/Product/Product"; // Asegúrate de importar updateProductBackend
import { toast } from "react-toastify";
import { useCallback, useEffect, useState } from "react";

export const UpdateProductPopup = ({ idProduct, isOpen, onCancel }) => {
  const [productData, setProductData] = useState(null);
  const [subcategorias, setSubcategorias] = useState([]);

  const estadoProducto = {
    nuevo: "Nuevo",
    usado: "Seminuevo",
    aceptable: "Aceptable",
    deteriorado: "Deteriorado",
  };

  const [formData, setFormData] = useState({
    nombre: "",
    descripcion: "",
    precio: "",
    subcategoria_id: "",
    estado: "",
  });

  const [disabledUpdate, setDisabledUpdate] = useState(true);

  const getIdFromName = useCallback((name, list) => {
    const item = list.find((item) => item.nombre === name);
    return item ? item.id : "";
  }, []);

  const getNameFromId = useCallback((id, list) => {
    const item = list.find((item) => item.id === id);
    return item ? item.nombre : "";
  }, []);

 const fetchProduct = useCallback(async () => {
    if (!idProduct || !isOpen) {
      setProductData(null);
      setFormData({
        nombre: "",
        descripcion: "",
        precio: "",
        subcategoria_id: "",
        estado: "",
      });
      return;
    }

    try {
      const response = await getProduct(idProduct);
      if (response.status === 200) {
        const fetchedProduct = response.data.product;
        setProductData(fetchedProduct);

        let initialSubcategoryId = "";
        // Estas dependencias están dentro de la función y deben ser parte del array de useCallback
        if (subcategorias?.length > 0 && fetchedProduct.subcategoria) {
          initialSubcategoryId = getIdFromName(
            fetchedProduct.subcategoria,
            subcategorias
          );
        }

        setFormData({
          nombre: fetchedProduct.nombre || "",
          descripcion: fetchedProduct.descripcion || "",
          precio: fetchedProduct.precio || "",
          subcategoria_id: initialSubcategoryId,
          estado: fetchedProduct.estado || "",
        });
      } else {
        toast.error("No se ha podido acceder al producto, prueba más tarde.");
        setProductData(null);
        setFormData({
          nombre: "",
          descripcion: "",
          precio: "",
          subcategoria_id: "",
          estado: "",
        });
      }
    } catch (error) {
      toast.error("No se ha podido acceder al producto, prueba más tarde.");
      setProductData(null);
      setFormData({
        nombre: "",
        descripcion: "",
        precio: "",
        subcategoria_id: "",
        estado: "",
      });
      console.error("Error al cargar el producto: ", error);
    }
  }, [idProduct, isOpen, subcategorias, getIdFromName]);

  const fetchSubcategorias = useCallback(async () => {
    try {
      const response = await getSubcategories();
      if (response.status === 200) {
        setSubcategorias(response.data.subcategorias);
      } else {
        console.error(
          "Error al obtener las subcategorías:",
          response.data.message
        );
        toast.error("Error al obtener las subcategorías");
      }
    } catch (error) {
      toast.error("Error al obtener las subcategorías");
      console.error("Error al obtener las subcategorías:", error);
    }
  }, []);

  useEffect(() => {
    fetchSubcategorias();
  }, [fetchSubcategorias]);

  useEffect(() => {
    if (isOpen && idProduct && subcategorias?.length > 0) {
      fetchProduct();
    } else if (!isOpen) {
      setProductData(null);
      setFormData({
        nombre: "",
        descripcion: "",
        precio: "",
        subcategoria_id: "",
        estado: "",
      });
    }
  }, [idProduct, isOpen, subcategorias, fetchProduct]);

  useEffect(() => {
    if (!productData || !isOpen) {
      setDisabledUpdate(true);
      return;
    }

    const currentPrice = Number(formData.precio);
    const originalPrice = Number(productData.precio);

    const isChanged =
      formData.nombre !== (productData.nombre || "") ||
      formData.descripcion !== (productData.descripcion || "") ||
      currentPrice !== originalPrice ||
      formData.subcategoria_id !==
        (getIdFromName(productData.subcategoria, subcategorias) || "") ||
      formData.estado !== (productData.estado || "");

    setDisabledUpdate(!isChanged);
  }, [formData, productData, isOpen, subcategorias, getIdFromName]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubcategoryChange = (e) => {
    const newSubcategoryId = e.target.value;
    setFormData((prevData) => ({
      ...prevData,
      subcategoria_id: newSubcategoryId,
    }));
  };

  const handleUpdateProduct = async () => {
    try {
      if (
        !formData.nombre ||
        !formData.precio ||
        !formData.subcategoria_id ||
        !formData.estado
      ) {
        toast.error("Por favor, rellena todos los campos obligatorios.");
        return;
      }
          const subcategoriaNombre = getNameFromId(formData.subcategoria_id, subcategorias);
          if (!subcategoriaNombre) {
        toast.error("Subcategoría seleccionada no válida.");
        return;
      }


      const dataToSend = {
        id: Number(idProduct), 
        nombre: formData.nombre,
        descripcion: formData.descripcion,
        precio: Number(formData.precio),
        subcategoria: subcategoriaNombre, 
        estado: formData.estado,
      };
      const response = await updateProduct(dataToSend);
      if (response.status === 200) {
        toast.success("Producto actualizado correctamente.");
        onCancel();
      } else {
        toast.error("Error al actualizar el producto.");
        console.error("Error updating product:", response.data.message);
      }
    } catch (error) {
      toast.error("Error de conexión al actualizar el producto.");
      console.error("Error updating product:", error);
    }
  };

  return (
    <Dialog
      open={isOpen}
      onClose={onCancel}
      maxWidth="sm"
      fullWidth
      sx={{ display: "flex", flexDirection: "column" }}
    >
      <DialogTitle sx={{ textAlign: "center" }}>
        Actualizar producto
      </DialogTitle>
      <DialogContent>
        <Box sx={{ display: "flex", flexDirection: "column", gap: 2, p: 2 }}>
          <TextField
            label="Nombre"
            name="nombre"
            value={formData.nombre}
            onChange={handleInputChange}
            fullWidth
            disabled={!productData}
          />
          <TextField
            label="Descripción"
            name="descripcion"
            value={formData.descripcion}
            onChange={handleInputChange}
            fullWidth
            multiline
            rows={4}
            disabled={!productData}
          />
          <TextField
            label="Precio"
            name="precio"
            value={formData.precio}
            onChange={handleInputChange}
            fullWidth
            type="number"
            inputProps={{ step: "0.01" }}
            disabled={!productData}
          />

          <FormControl fullWidth disabled={!productData}>
            <InputLabel id="subcategorias-select-label">
              Subcategoría
            </InputLabel>
            <Select
              labelId="subcategorias-select-label"
              id="subcategorias-select"
              name="subcategoria_id"
              value={formData.subcategoria_id}
              onChange={handleSubcategoryChange}
              input={<OutlinedInput label="Subcategoría" />}
            >
              <MenuItem value="">
                <em>Selecciona una subcategoría</em>
              </MenuItem>
              {subcategorias?.map((subcategoria) => (
                <MenuItem key={subcategoria.id} value={subcategoria.id}>
                  {subcategoria.nombre}
                </MenuItem>
              ))}
            </Select>
          </FormControl>

          {/* Selector de Estado */}
          <FormControl fullWidth disabled={!productData}>
            <InputLabel id="estado-select-label">Estado</InputLabel>
            <Select
              labelId="estado-select-label"
              id="estado-select"
              name="estado"
              value={formData.estado}
              onChange={handleInputChange}
              input={<OutlinedInput label="Estado" />}
            >
              <MenuItem value="">
                <em>Selecciona el estado</em>
              </MenuItem>
              {Object.values(estadoProducto).map((estadoValor) => (
                <MenuItem key={estadoValor} value={estadoValor}>
                  {estadoValor}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
        </Box>
      </DialogContent>
      <DialogActions>
        <Button onClick={onCancel} color="primary">
          Cancelar
        </Button>
        <Button
          onClick={handleUpdateProduct}
          color="primary"
          disabled={disabledUpdate}
        >
          Actualizar
        </Button>
      </DialogActions>
    </Dialog>
  );
};
