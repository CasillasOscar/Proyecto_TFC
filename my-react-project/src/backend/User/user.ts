import { axiosPrivate } from "../axios";

const CONTROLLER = 'users/'

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
