package pl.edu.pjwstk.jaz.zad2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jaz.zad2.exception.UserAlreadyExistsException;

import java.util.Arrays;
import java.util.List;

@RestController
public class RegisterController {

    RegisteredUsers registeredUsers;

    @Autowired
    public RegisterController(RegisteredUsers registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) throws UserAlreadyExistsException {
        if(!registeredUsers.checkIfUserIsInDB(registerRequest.getUsername())) {
            registeredUsers.add(registerRequest.getUsername(), registerRequest.getPassword());
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
