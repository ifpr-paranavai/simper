import axios from "axios";

class BaseService<T> {
    PATH_URL: string = '';

    constructor(
        versionAPI: string,
        mapping: string,
    ) {
        this.PATH_URL = `${process.env.NEXT_PUBLIC_API}/${versionAPI}/${mapping}`;
    }

    save = (obj: T) => {
        return axios.post(this.PATH_URL, obj);
    }

    saveAll = (obj: T[]) => {
        return axios.post(this.PATH_URL, obj);
    }

    update = (obj: T) => {
        return axios.put(this.PATH_URL, obj);
    }

    delete = (id: number) => {
        return axios.delete(this.PATH_URL, { 
            params: { id: id } 
        });
    }

    findById = (id: number) => {
        return axios.get(this.PATH_URL, { 
            params: { id: id } 
        });
    }

    findAll = () => {
        return axios.get(this.PATH_URL);
    }
}

export default BaseService;