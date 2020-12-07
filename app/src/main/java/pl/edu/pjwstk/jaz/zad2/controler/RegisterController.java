package pl.edu.pjwstk.jaz.zad2.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jaz.zad2.request.RegisterRequest;
import pl.edu.pjwstk.jaz.zad2.RegisteredUsers;
import pl.edu.pjwstk.jaz.zad2.user.UserService;
import pl.edu.pjwstk.jaz.zad2.exception.UserAlreadyExistsException;

import java.util.Arrays;
import java.util.List;

@RestController
public class RegisterController {

    private final RegisteredUsers registeredUsers;
    private final UserService userService;

    @Autowired
    public RegisterController(RegisteredUsers registeredUsers, UserService userService) {
        this.registeredUsers = registeredUsers;
        this.userService = userService;
    }

//    @PostMapping("/register")
//    public void register(@RequestBody RegisterRequest registerRequest) throws UserAlreadyExistsException {
//        if(!registeredUsers.checkIfUserIsInDB(registerRequest.getUsername())) {
//            registeredUsers.add(registerRequest.getUsername(), registerRequest.getPassword());
//            System.out.println("Dodano");
//        }else {
//            throw new UserAlreadyExistsException("User already exists");
//        }
//
//    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) throws UserAlreadyExistsException {
        if(userService.findUserByUsername(registerRequest.getUsername()) == null) {
            //registeredUsers.add(registerRequest.getUsername(), registerRequest.getPassword());
            userService.saveUser(registerRequest.getUsername()
                                ,registerRequest.getPassword());
            System.out.println("Dodano");
        }else {
            throw new UserAlreadyExistsException("User already exists");
        }

    }

    //@PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/registerList")
    public List getAllAcc(){
        return Arrays.asList(registeredUsers.getRegisteredUsers());
    }

}
