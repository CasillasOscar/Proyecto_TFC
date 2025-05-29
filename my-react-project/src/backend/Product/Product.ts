import { axiosPrivate } from "../axios";

const CONTROLLER = 'products/'

export const getSubcategories = async () => {
  const response = await axiosPrivate.get(
    `${CONTROLLER}listSubcategorias`
  );
  return response;
}

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
        formData);
    return response;
    }