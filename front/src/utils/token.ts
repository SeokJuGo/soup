import { ACCESS_TOKEN, REFRESH_TOKEN } from '@/constants/token'
import axios from 'axios'
import { destroyCookie, parseCookies, setCookie } from 'nookies'

const getAccessToken = () => parseCookies().accessToken

const getRefreshToken = () => parseCookies().refreshToken

const setToken = (accessToken: any, refreshToken: any) => {
  setCookie(null, 'accessToken', accessToken)
  setCookie(null, 'refreshToken', refreshToken)
}

const tokenClear = () => {
  destroyCookie(null, ACCESS_TOKEN)
  destroyCookie(null, REFRESH_TOKEN)
}

const tokenRefresh = async () => {
  const token = getRefreshToken()
  const instance = axios.create({
    baseURL: process.env.NEXT_PUBLIC_BACKEND_BASE_URL,
  })

  try {
    console.log('내가 보낸 토큰==', token)
    const res = await instance.post('/auth/token/refresh', {
      refreshToken: token,
    })
    const data = res.data.result
    console.log('받아온 데이터==', data)
    setToken(data.accessToken, data.refreshToken)
  } catch (e) {
    console.log('refresh error==', e)
    tokenClear()
    window.location.href = '/'
  }
}

export { getAccessToken, getRefreshToken, setToken, tokenClear, tokenRefresh }
