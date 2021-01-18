package pl.edu.pjwstk.jaz.zad2.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.zad2.services.CategoryService;

import java.util.List;

@RestController
public class CategoriesController {
    CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/showCategories")
    public List showCategories(){
        return categoryService.showCategories();
    }


}
