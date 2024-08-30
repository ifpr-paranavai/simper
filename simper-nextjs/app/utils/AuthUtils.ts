export class AuthUtils {
    static setToken(token: string) {
        localStorage.setItem("token", `Bearer ${token}`);
    }

    static getToken() {
        return localStorage.getItem("token");
    }

    static clearToken() {
        localStorage.removeItem("token");
    }
}