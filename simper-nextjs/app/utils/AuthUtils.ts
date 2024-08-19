export class AuthUtils {
    setToken(token: string) {
        localStorage.setItem("token", `Bearer ${token}`);
    }

    clearToken() {
        localStorage.removeItem("token");
    }
}