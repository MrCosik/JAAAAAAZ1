package pl.edu.pjwstk.jaz.zad2.request;

import java.util.List;

public class SectionRequest {

    private String title;
    private List<String> categories;

    public SectionRequest() {
    }

    public SectionRequest(String title, List<String> categories) {
        this.title = title;
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
