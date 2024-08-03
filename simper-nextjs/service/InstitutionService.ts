import axios from "axios";
import { Institution } from "@/model/Institution";

class InstitutionService {
    PATH_URL = `${process.env.API}/v1/institutions`;

    save = (obj: Institution) => {
        return axios.post(this.PATH_URL, obj);
    }
}

export default InstitutionService;
