package pl.edu.pjwstk.jaz.zad2.endpoints;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Edit {


    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/edit")
    public void edit(){

    }
}
