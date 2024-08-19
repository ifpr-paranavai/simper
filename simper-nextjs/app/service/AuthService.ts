import axios from "axios";
import { AuthRequest } from "../model/AuthRequest";

class AuthService {
    PATH_URL = `${process.env.NEXT_PUBLIC_API}/v1/auth`;

    createAuthenticationToken = (obj: AuthRequest) => {
        return axios.post(`${this.PATH_URL}`, obj);
    }
}

export default AuthService;
