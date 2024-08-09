import axios from "axios";
import { UserPasswordReset } from "../model/UserPasswordReset";
import { UserPasswordResetRequest } from "../model/UserPasswordResetRequest";

class UserAccountService {
    PATH_URL = `${process.env.NEXT_PUBLIC_API}/v1/user-account`;

    setPassword = (obj: UserPasswordReset) => {
        return axios.post(`${this.PATH_URL}/set-password`, obj);
    }

    changePassword = (obj: UserPasswordResetRequest) => {
        return axios.post(`${this.PATH_URL}/change-password`, obj);
    }
}

export default UserAccountService;
