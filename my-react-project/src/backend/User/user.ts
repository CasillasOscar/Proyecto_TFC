import { axiosPrivate } from "../axios";

const CONTROLLER = 'users/'

export const updateAvatar = async (nickname: string, file: File) => {
  const formData = new FormData();
  formData.append("imagen", file);
  console.log(formData);

  const response = await axiosPrivate.post(
    `${CONTROLLER}updateProfilePhoto/${nickname}`,
    formData
  );
  return response;
};
