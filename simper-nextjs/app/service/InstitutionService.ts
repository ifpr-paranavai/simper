import axios from "axios";
import { Institution } from "@/app/model/Institution";

class InstitutionService {
    PATH_URL = `${process.env.NEXT_PUBLIC_API}/v1/institutions`;

    save = (obj: Institution) => {
        return axios.post(this.PATH_URL, obj);
    }
}

export default InstitutionService;
