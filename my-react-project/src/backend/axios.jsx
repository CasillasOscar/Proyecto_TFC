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
    //Revisar si deberia ser 403 o 401
    if (error.response.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;
      try {
        const response = await axios.post(`${URL}auth/refresh`, {
          token: localStorage.getItem("token_refresh"),
        });
        localStorage.setItem("token", response.data.access_token);
        originalRequest.headers.Authorization = `Bearer ${response.data.access_token}`;
        return axiosPrivate(originalRequest);
      } catch (err) {
        console.error(err);
      }
    }
    return Promise.reject(error);
  }
);

//Axios public es para las peticiones que no requieren autenticacion
export const axiosPublic = axios.create({
  baseURL: URL,
  withCredentials: false,
});


axiosPublic.interceptors.request.use(
  (response) => response,
  (error) => {
    return Promise.reject(error);
  }
);
