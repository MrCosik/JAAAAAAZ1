package pl.edu.pjwstk.jaz.zad2.endpoints;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Explore {


@PreAuthorize("hasAnyAuthority('user')")
    @GetMapping("/explore")
    public void explore(){

    }
}
