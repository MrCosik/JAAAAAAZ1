package pl.edu.pjwstk.jaz.zad2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    RegisteredUsers registeredUsers;

    @Autowired
    public RegisterController(RegisteredUsers registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest){
            registeredUsers.add(registerRequest.getUsername(),registerRequest.getPassword());
            System.out.println("Dodano");
    }

}
