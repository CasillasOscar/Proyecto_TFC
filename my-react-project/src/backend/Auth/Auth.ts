import { axiosPrivate, axiosPublic } from "../axios";

export const signIn = async (
  nickname: string,
  nombre: string,
  apellido: string,
  password: string,
  telefono: number,
  email: string
) => {
  const response = await axiosPublic.post("auth/signin", {
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
  const response = await axiosPublic.post("auth/login", {
    email: email,
    password: password,
  });
  return response;
};

export const logout = async () => {
  const response = await axiosPrivate.get("auth/logout");
  return response;
};
