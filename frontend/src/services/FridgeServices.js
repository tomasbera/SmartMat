import axios from "axios";
import SessionToken from '@/features/SessionToken.js'

const BASE_LISTING_URL = "http://10.22.58.166:8080/fridge";

export const getAllFridges = async (username) => {
    return await axios.get(`${BASE_LISTING_URL}/loadAll?user=${username}`);
}

export const getFridgeById = async(fridgeID) => {
    return await axios.get(`${BASE_LISTING_URL}/user/load?fridgeID=${fridgeID}`);
}

export const addNewFridge = async (fridgeName) => {
    await axios.post(`${BASE_LISTING_URL}/create?fridgeName=${fridgeName}`,{},{
        headers: {
            Authorization: `Bearer ${await SessionToken()}`,
        }
    });
}

export const updateFridge = async (fridgeDTO) => {
    await axios.put(`${BASE_LISTING_URL}/update`, fridgeDTO, {
        headers: {
            Authorization: `Bearer ${await SessionToken()}`,
        }
    });
}

export const deleteUserFromFridge = async (fridgeUserDTO) => {
    console.log(fridgeUserDTO);
    await axios.delete(`${BASE_LISTING_URL}/delete/user`, {
        data: fridgeUserDTO,
        headers: {
            Authorization: `Bearer ${await SessionToken()}`,
        }
    });
}

