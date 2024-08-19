export class AuthUtils {
    setToken(token: string) {
        localStorage.setItem("token", token);
    }

    clearToken() {
        localStorage.removeItem("token");
    }
}