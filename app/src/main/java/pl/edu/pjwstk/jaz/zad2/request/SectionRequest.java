package pl.edu.pjwstk.jaz.zad2.request;

import java.util.List;

public class SectionRequest {

    private final String title;
    private final List<String> categories;

    public SectionRequest(String title, List<String> categories) {
        this.title = title;
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getCategories() {
        return categories;
    }
}
