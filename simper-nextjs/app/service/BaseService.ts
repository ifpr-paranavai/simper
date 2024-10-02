import axios, { AxiosRequestConfig } from "axios";
import { AuthUtils } from "../utils/AuthUtils";

class BaseService<T> {
    PATH_URL: string = '';

    constructor(
        versionAPI: string,
        mapping: string,
    ) {
        this.PATH_URL = `${process.env.NEXT_PUBLIC_API}/${versionAPI}/${mapping}`;
    }

    config: AxiosRequestConfig = {
        headers: { Authorization: AuthUtils.getToken.toString() }
    }

    save = (obj: T) => {
        return axios.post(this.PATH_URL, obj, this.config);
    }

    saveAll = (obj: T[]) => {
        return axios.post(this.PATH_URL, obj, this.config);
    }

    update = (obj: T) => {
        return axios.put(this.PATH_URL, obj, this.config);
    }

    delete = (id: number) => {
        const data: AxiosRequestConfig = this.config.data = { params: { id: id } };
        return axios.delete(this.PATH_URL, data);
    }

    findById = (id: number) => {
        const data: AxiosRequestConfig = this.config.data = { params: { id: id } };
        return axios.get(this.PATH_URL, data);
    }

    findAll = () => {
        return axios.get(this.PATH_URL);
    }
}

export default BaseService;