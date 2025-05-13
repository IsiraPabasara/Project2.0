package model;

public class User {
    private int userid;
    private String username;
    private String password;
    private String role;

    // Constructor with all fields

    public User(int userid, String username, String password, String role) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Constructor for registration (no userid and role needed)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "staff"; // Default role set to staff
    }

    // Getters and Setters
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
