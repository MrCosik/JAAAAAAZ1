package pl.edu.pjwstk.jaz.zad2.request;

import pl.edu.pjwstk.jaz.zad2.entities.CategoryEntity;

public class AuctionRequest {

    private String title;
    private String description;
    private CategoryEntity categoryEntity;

    public AuctionRequest(String title, String description, CategoryEntity...categoryEntity) {
        this.title = title;
        this.description = description;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }
}
