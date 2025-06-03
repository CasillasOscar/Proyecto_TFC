import axios from "axios";
import { URL } from "./utils.jsx";

//axiosPrivate es para las peticiones que requieren autenticacion
export const axiosPrivate = axios.create({
  baseURL: URL,
});

axiosPrivate.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

axiosPrivate.interceptors.response.use(
  (response) => {
    return response;
  },
  async (error) => {
    const originalRequest = error.config;

    if (
      error.response &&
      error.response.status === 403 &&
      !originalRequest._retry
    ) {
      originalRequest._retry = true;
      try {
        const refreshToken = localStorage.getItem("token_refresh");
        if (!refreshToken) {
          return Promise.reject(error);
        }

        const response = await axios.post(`${URL}auth/refresh`, {
          token: refreshToken,
        });
        localStorage.setItem("token_access", response.data.access_token);
        originalRequest.headers.Authorization = `Bearer ${response.data.access_token}`;

        return axiosPrivate(originalRequest);
      } catch (err) {
        console.error("Error al refrescar el token:", err);
        return Promise.reject(err);
      }
    }
     return Promise.reject(error);
  }
);

export const axiosPublic = axios.create({
  baseURL: URL,
  withCredentials: false
});

// axiosPublic.interceptors.request.use(
//   (config) => config,
//   (error) => {
//     return Promise.reject(error);
//   }
// );

axiosPublic.interceptors.response.use(
  (response) => {
    return response},
  (error) => {
     return error.response;
  }
);
