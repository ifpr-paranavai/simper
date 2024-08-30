export class AuthUtils {
    setToken(token: string) {
        localStorage.setItem("token", `Bearer ${token}`);
    }

    getToken() {
        return localStorage.getItem("token");
    }

    clearToken() {
        localStorage.removeItem("token");
    }
}