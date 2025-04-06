import {USER_MODULE} from './_prefix'
import axios from 'axios';

type LoginInfo = {
    phone: string,
    password: string
}

type RegisterInfo = {
    role: string,
    name: string,
    phone: number,
    password: string,
    storeId?: number,
    address: string,
    createTime: number,
    email: string,
    avatar: string,
}

type UpdateInfo = {
    name?: string,
    password?: string,
    address?: string,
}

// 如果有“Vue: This may be converted to an async function”警告，可以不管
// 用户登录
export const userLogin = (loginInfo: LoginInfo) => {
    return axios.post(`${USER_MODULE}/login`, null, {params: loginInfo})
        .then(res => {
            return res
        })
}

// 用户注册
export const userRegister = (registerInfo: RegisterInfo) => {
    return axios.post(`${USER_MODULE}/register`, registerInfo,
        {headers: {'Content-Type': 'application/json'}})
        .then(res => {
            return res
        })
}

function parseJwt(token: string) {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(
        atob(base64)
            .split('')
            .map(c => `%${('00' + c.charCodeAt(0).toString(16)).slice(-2)}`)
            .join('')
    );
    return JSON.parse(jsonPayload);
}
// 获取用户信息
export const userInfo = () => {
    const token :string|null = sessionStorage.getItem('token');
    return axios.get(`${USER_MODULE}/information`,{headers: {'token': token}})
        .then(res => {
            //console.log(res.data.result);
            return res
        })
}

// 更新用户信息
export const userInfoUpdate = (updateInfo: UpdateInfo) => {
    return axios.post(`${USER_MODULE}`, updateInfo, {headers: {'Content-Type': 'application/json'}})
        .then(res => {
            return res
        })
}