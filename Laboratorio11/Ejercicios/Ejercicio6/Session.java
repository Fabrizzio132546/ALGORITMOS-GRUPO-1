package lab11.ejercicios.ejercicio6;

public class Session implements Comparable<Session> {
    private String token;
    private String username;
    private String role;
    private long expiresAt;

    public Session(String token, String username, String role, long expiresAt) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.expiresAt = expiresAt;
    }

    public Session(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }
    
    public long getExpiresAt() {
        return expiresAt;
    }

    @Override
    public int compareTo(Session other) {
        return this.token.compareTo(other.token);
    }

    @Override
    public String toString() {
        return "Session[user=" + username + ", role=" + role + ", token=" + token + "]";
    }
}