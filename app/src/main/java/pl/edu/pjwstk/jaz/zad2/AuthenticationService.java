package pl.edu.pjwstk.jaz.zad2;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.jaz.zad2.controler.RegisterController;
import pl.edu.pjwstk.jaz.zad2.exception.AlreadyLoggedException;
import pl.edu.pjwstk.jaz.zad2.exception.UserDoesntExistException;
import pl.edu.pjwstk.jaz.zad2.user.UserService;
import pl.edu.pjwstk.jaz.zad2.user.UserSession;

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

    public boolean login(String username, String password) {
        var foundUser = userService.findUserByUsername(username);

        if (!userSession.isLoggedIn()) {
            if(foundUser != null) {
                if (foundUser.getUsername().equals(username) && userService.getPasswordEncoder().matches(password, foundUser.getPassword())) {
                    userSession.logIn();
                    SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(foundUser));
                    return true;
                }
            }else{
                throw new UserDoesntExistException("This username is not registered");
            }
        } else {
            throw new AlreadyLoggedException("User already logged");
        }
        return false;
    }
}
