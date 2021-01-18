package pl.edu.pjwstk.jaz.zad2.controler;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("/newSection")
    public void createNewSection(@RequestBody SectionRequest sectionRequest){
        sectionService.createSection(sectionRequest);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("/editSection/{id}")
    public void updateSection(@PathVariable Long id, @RequestBody SectionRequest sectionRequest){
        sectionService.updateSection(id,sectionRequest);
    }
}
