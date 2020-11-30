package pl.edu.pjwstk.jaz.zad2;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
//    private String roles;
    private Set<String> roles = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        roles.add("user");
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void addRole(String role){
        roles.add(role);
    }
}
