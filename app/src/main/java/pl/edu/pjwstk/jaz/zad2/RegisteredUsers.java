package pl.edu.pjwstk.jaz.zad2;

import liquibase.pro.packaged.U;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class RegisteredUsers {
    private final Map<String, User> registeredUsers;

    public RegisteredUsers() {
        registeredUsers = new HashMap<>();
        User admin = new User("admin","admin");
        admin.addRole("admin");
        admin.addRole("user");
        registeredUsers.put("admin",admin);
    }

    public void add(String username, String password){
        registeredUsers.put(username,new User(username,password));
    }

    public boolean checkIfUserIsInDB(String username){
        return registeredUsers.containsKey(username);
    }

    public boolean checkIfUsersPasswordIsCorrect(String username, String password){
        return registeredUsers.get(username).getPassword().equals(password);
    }

    public Set<String> getUsersRole(String username){
        return registeredUsers.get(username).getRoles();
    }

    public User getUserFromMap(String username){
        return registeredUsers.get(username);
    }

    public Map<String, User> getRegisteredUsers() {
        return registeredUsers;
    }
}
