import { axiosPrivate, axiosPublic } from "../axios";

const CONTROLLER = "products/";

export const listProducts = async () => {
  const response = await axiosPublic.get(`${CONTROLLER}`);
  return response;
};

export const getImageProduct = async (path: string) => {
  const response = await axiosPublic.post(
    `${CONTROLLER}imageProduct`,
    {}, 
    {
      params: {
        path: path,
      },
      responseType: "blob",
    }
  );
  const imageUrl = URL.createObjectURL(response.data);
  return imageUrl;
};

export const getProduct = async (id: string) => {
  const response = await axiosPrivate.get(`${CONTROLLER}${id}`);
  return response;
};

export const getSubcategories = async () => {
  const response = await axiosPrivate.get(`${CONTROLLER}listSubcategorias`);
  return response;
};

export const userSellProduct = async (
  nickname: string,
  image1: File,
  image2: File,
  data: any
) => {
  console.log(JSON.stringify(data));

  const formData = new FormData();
  formData.append("imagen1", image1);
  formData.append("imagen2", image2);
  formData.append("sellProductDTO", JSON.stringify(data));

  const response = await axiosPrivate.post(
    `${CONTROLLER}${nickname}/sellProduct`,
    formData
  );
  return response;
};

export const saveFavorite = async (nickname: string, idProdcuto: number)=>{
  const response = await axiosPrivate.get(`${CONTROLLER}addFavorite/${nickname}/${idProdcuto}`)

  return response
}