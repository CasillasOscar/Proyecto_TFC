import { axiosPrivate, axiosPublic } from "../axios";

const CONTROLLER = "auth/";

export const signIn = async (
  nickname: string,
  nombre: string,
  apellido: string,
  password: string,
  telefono: number,
  email: string
) => {
  const response = await axiosPublic.post(`${CONTROLLER}signin`, {
    nickname: nickname,
    nombre: nombre,
    apellido: apellido,
    password: password,
    telefono: telefono,
    email: email,
  });
  return response;
};

export const login = async (email: string, password: string) => {
  const response = await axiosPublic.post(`${CONTROLLER}login`, {
    email: email,
    password: password,
  });
  return response;
};

export const logout = async () => {
  const response = await axiosPrivate.get(`${CONTROLLER}logout`);
  return response;
};
