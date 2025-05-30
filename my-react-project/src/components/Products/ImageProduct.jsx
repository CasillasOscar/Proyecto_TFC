import { useEffect, useRef, useState } from "react";
import { CardMedia } from "@mui/material";
import { toast } from "react-toastify";
import { getImageProduct } from "../../backend/Product/product";

export const ImageProduct = ({ producto }) => {
  const [imageUrl, setImageUrl] = useState(null);
  const [loading, setLoading] = useState(true);
  const IMG_DEFAULT =
    "https://images.icon-icons.com/3001/PNG/512/default_filetype_file_empty_document_icon_187718.png";

   const blobUrlToRevoke = useRef(null);

  useEffect(() => {
    setLoading(true);
    setImageUrl(null); 
    
    if (blobUrlToRevoke.current) {
      URL.revokeObjectURL(blobUrlToRevoke.current);
      blobUrlToRevoke.current = null; 
    }

    const fetchImage = async () => {
      try {
        const url = await getImageProduct(producto.imagen1);
        
        if (url && url.startsWith("blob:")) {
          blobUrlToRevoke.current = url;
        } else {
          blobUrlToRevoke.current = null;
        }

        setImageUrl(url); 
      } catch (error) {
        console.error("Error al cargar la imagen:", error);
        toast.error("Error al cargar la imagen del producto.");
        setImageUrl(IMG_DEFAULT); 
      } finally {
        setLoading(false);
      }
    };

    fetchImage();

    return () => {
      if (blobUrlToRevoke.current) {
        URL.revokeObjectURL(blobUrlToRevoke.current);
        blobUrlToRevoke.current = null; 
      }
    };
  }, [producto.id, producto.imagen1, IMG_DEFAULT]);

  return loading ? (
    <CardMedia
      component="img"
      height="200"
      image={IMG_DEFAULT}
      alt={producto.nombre}
      sx={{ objectFit: "cover" }}
    />
  ) : (
    <CardMedia
      component="img"
      height="200"
      image={imageUrl}
      alt="Imagen por defecto"
      sx={{ objectFit: "cover" }}
    />
  );
};
