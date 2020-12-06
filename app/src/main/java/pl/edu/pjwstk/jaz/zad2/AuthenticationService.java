package pl.edu.pjwstk.jaz.zad2;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.jaz.zad2.exception.AlreadyLoggedException;

@Component
public class AuthenticationService {

    final UserSession userSession;
    final RegisterController registerController;
    final RegisteredUsers registeredUsers;
    final UserService userService;


    public AuthenticationService(UserSession userSession, RegisterController registerController, RegisteredUsers registeredUsers, UserService userService) {
        this.userSession = userSession;
        this.registerController = registerController;
        this.registeredUsers = registeredUsers;
        this.userService = userService;
    }

//    public boolean login(String username, String password){
//        if(!userSession.isLoggedIn()){
//        if((!username.isEmpty() && !password.isEmpty())) {
//            if(registerController.registeredUsers.checkIfUserIsInDB(username) && registerController.registeredUsers.checkIfUsersPasswordIsCorrect(username, password)){
//                userSession.logIn();
//                SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(registeredUsers.getUserFromMap(username)));
//                return true;
//            }
//            return false;
//        }
//        } else{
//            throw new AlreadyLoggedException("User already logged");
//        }
//        return false;
//    }

    public boolean login(String username, String password) {
        var foundUser = userService.findUserByUsername(username);

        if (!userSession.isLoggedIn()) {
            if (foundUser != null) {
                if (foundUser.getUsername().equals(username) && foundUser.getPassword().equals(password)) {
                    userSession.logIn();
                    SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(registeredUsers.getUserFromMap(username)));
                    return true;
                }
            }
        } else {
            throw new AlreadyLoggedException("User already logged");
        }
        return false;
    }
}
