// frontend/src/utils/auth.ts
import axios from 'axios';
import {api} from "boot/axios";

// parameter extraction
export const getCodeFromUrl = () => {
  const urlParams = new URLSearchParams(window.location.search);
  return urlParams.get('code');
};

export const exchangeCodeForToken = async (code: string) => {
  const params = new URLSearchParams({
    grant_type: 'authorization_code',
    code: code,
    redirect_uri: 'http://localhost:9000',
    client_id: 'multi',
    scope: 'openid'
  });

  const authHeader = 'Basic ' + btoa('multi:lucasmulti');

  try {
    const response = await axios.post('http://localhost:9999/oauth2/token', params, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': authHeader
      }
    });
    console.log('Access Token:', response.data);
    setToken(response.data.access_token, response.data.refresh_token);
  } catch (error) {
    console.error('Error exchanging code for token:', error);
  } finally {
    window.history.replaceState({}, document.title, window.location.origin);
  }
};

const setToken = (atToken: string, rtToken: string) => {
  localStorage.setItem('accessToken', atToken); // Access Token
  document.cookie = `refreshToken=${rtToken}; Secure; HttpOnly; SameSite=Strict`; // Refresh Token: Only accessible by the server
}

// export const logout = async () => {
//   // Axios로 인증 서버에 토큰 무효화 요청
//   try {
//     const accessToken = localStorage.getItem('accessToken');
//     const response = await axios.post('http://localhost:9999/oauth/revoke', null, {
//       headers: {
//         'Authorization': `Bearer ${accessToken}`
//       }
//     }).then(() => {
//       localStorage.removeItem('accessToken');
//       document.cookie = "refreshToken=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
//     });
//     console.log('Token revoked successfully:', response);
//   } catch (error) {
//     console.error('Error revoking token:', error);
//   }
// }
