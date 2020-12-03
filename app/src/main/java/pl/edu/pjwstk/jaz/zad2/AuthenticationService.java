package pl.edu.pjwstk.jaz.zad2;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.jaz.zad2.exception.AlreadyLoggedException;

@Component
public class AuthenticationService {

    final UserSession userSession;
    final RegisterController registerController;
    final RegisteredUsers registeredUsers;


    public AuthenticationService(UserSession userSession, RegisterController registerController, RegisteredUsers registeredUsers) {
        this.userSession = userSession;
        this.registerController = registerController;
        this.registeredUsers = registeredUsers;
    }

    public boolean login(String username, String password){
        if(!userSession.isLoggedIn()){
        if((!username.isEmpty() && !password.isEmpty())) {
            if(registerController.registeredUsers.checkIfUserIsInDB(username) && registerController.registeredUsers.checkIfUsersPasswordIsCorrect(username, password)){
                userSession.logIn();
                SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(registeredUsers.getUserFromMap(username)));
                return true;
            }
            return false;
        }
        } else{
            throw new AlreadyLoggedException("USer already logged");
        }
        return false;
    }
}
