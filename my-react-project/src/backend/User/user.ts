import { axiosPrivate } from "../axios";

const CONTROLLER = 'users/'

export const updateUser = async (nickname: string, userData: any) => {
  const response = await axiosPrivate.post(
    `${CONTROLLER}updateUser/${nickname}`,
    userData
  );
  return response;
}

export const updateAvatar = async (nickname: string, file: File) => {
  const formData = new FormData();
  formData.append("imagen", file);

  const response = await axiosPrivate.post(
    `${CONTROLLER}updateProfilePhoto/${nickname}`,
    formData
  );
  return response;
};

export const getAvatar = async (nickname: string) => {
    const response = await axiosPrivate.get(
        `${CONTROLLER}profilePhoto/${nickname}`,
        {
        responseType: 'blob' 
        }
    );
    
    const imageUrl = URL.createObjectURL(response.data);
    return imageUrl;
    }

    export const getListProvincias = async () => {
        const response = await axiosPrivate.get(
            `${CONTROLLER}listProvincias`
        );
        return response;
    }

    export const updateProvincia = async (nickname: string, provincia: string) => {
        const response = await axiosPrivate.post(
            `${CONTROLLER}updateProvincia/${nickname}`,
            { provincia }
        );
        return response;
    }

    export const listFavorites = async (nickname:string)=>{
      const response = await axiosPrivate.get(`${CONTROLLER}${nickname}/favorites`)
      return response;
    }

    export const listProductsFavorites = async (nickname:string)=>{
      const response = await axiosPrivate.get(`${CONTROLLER}${nickname}/listProductsFavorites`)
      return response;
    }

    export const removeFavorite =  async (nickname:string, productId: number)=>{
      const response = await axiosPrivate.get(`${CONTROLLER}${nickname}/removeFavorite/${productId}`)
      return response;
    }

    export const getPurchases = async (nickname:string)=>{
        const response = await axiosPrivate.get(`${CONTROLLER}compras/${nickname}`)
      return response;
    }

     export const getSales= async (nickname:string)=>{
        const response = await axiosPrivate.get(`${CONTROLLER}ventas/${nickname}`)
      return response;
    }