import { boot } from 'quasar/wrappers';
import axios, { AxiosInstance } from 'axios';

declare module '@vue/runtime-core' {
  interface ComponentCustomProperties {
    $axios: AxiosInstance;
    $api: AxiosInstance;
  }
}

const isTest = true; // false: local, true: server

const api = axios.create({ baseURL: isTest ? 'http://127.0.0.1:8088' : '' }); // TODO: domain 존재시?

// Retrieve tokens
const accessToken = localStorage.getItem('accessToken');
const refreshToken = document.cookie.split('; ').find(row => row.startsWith('refreshToken='))?.split('=')[1];

// Set up Axios interceptors
api.interceptors.request.use(config => {
  if (accessToken) {
    config.headers['Authorization'] = `Bearer ${accessToken}`;
  }
  if (refreshToken) {
    config.headers['x-refresh-token'] = refreshToken;
  }
  return config;
}, error => {
  return Promise.reject(error);
});

export default boot(({ app }) => {
  // for use inside Vue files (Options API) through this.$axios and this.$api

  app.config.globalProperties.$axios = axios;
  // ^ ^ ^ this will allow you to use this.$axios (for Vue Options API form)
  //       so you won't necessarily have to import axios in each vue file

  app.config.globalProperties.$api = api;
  // ^ ^ ^ this will allow you to use this.$api (for Vue Options API form)
  //       so you can easily perform requests against your app's API
});

export { api, axios };
