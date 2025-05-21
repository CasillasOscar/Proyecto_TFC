import { axiosPrivate, axiosPublic } from "../axios";

export const signIn = async (
  nickname: string,
  nombre: string,
  apellido: string,
  password: string,
  telefono: number,
  email: string
) => {
  try {
    const response = await axiosPublic.post("auth/signin", {
      nickname: nickname,
      nombre: nombre,
      apellido: apellido,
      password: password,
      telefono: telefono,
      email: email,
    });
    return response;
  } catch (error) {
    console.error("Error during sign-in:", error);
  }
};

export const login = async (email: string, password: string) => {
  try {
    const response = await axiosPublic.post("auth/login", {
      email: email,
      password: password,
    });
    return response;
  } catch (error) {
    console.error("Error during login:", error);
  }
}

export const logout = async () => {
  try {
    const response = await axiosPrivate.get("auth/logout");
    return response;
  } catch (error) {
    console.error("Error during logout:", error);
  }
};

