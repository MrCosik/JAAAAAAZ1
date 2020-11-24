package pl.edu.pjwstk.jaz.zad2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthenticationService {

    UserSession userSession;
    RegisterController registerController;

    public AuthenticationService(UserSession userSession, RegisterController registerController) {
        this.userSession = userSession;
        this.registerController = registerController;
    }

    public boolean login(String username, String password){
        if(!username.isEmpty() && !password.isEmpty()){
            userSession.logIn();
            return registerController.registeredUsers.checkIfUserIsInDB(username) && registerController.registeredUsers.checkIfUsersPasswordIsCorrect(username, password);
        }
        return false;
    }
}
