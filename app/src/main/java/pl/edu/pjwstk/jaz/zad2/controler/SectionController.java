package pl.edu.pjwstk.jaz.zad2.controler;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.zad2.request.SectionRequest;
import pl.edu.pjwstk.jaz.zad2.services.SectionService;

@RestController
public class SectionController {

    SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

//    @Transactional
    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("/newSection")
    public void createNewSection(@RequestBody SectionRequest sectionRequest){
        sectionService.createSection(sectionRequest);
    }
}
