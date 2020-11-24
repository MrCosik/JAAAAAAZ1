package pl.edu.pjwstk.jaz.zad2;

import liquibase.pro.packaged.U;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RegisteredUsers {
    private Map<String, User> registeredUsers;

    public RegisteredUsers() {
        registeredUsers = new HashMap<>();

        registeredUsers.put("admin", new User("admin","admin"));
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
}
