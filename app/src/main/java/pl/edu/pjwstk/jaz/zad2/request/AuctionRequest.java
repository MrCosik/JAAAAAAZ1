package pl.edu.pjwstk.jaz.zad2.request;

import pl.edu.pjwstk.jaz.zad2.entities.CategoryEntity;

import java.util.List;

public class AuctionRequest {

    private String title;
    private String description;
    private List<String> categoryEntity;
    private List<String> photos;


    public AuctionRequest(String title, String description, List<String> categoryEntity, List<String> photos) {
        this.title = title;
        this.description = description;
        this.categoryEntity = categoryEntity;
        this.photos = photos;
    }

    public AuctionRequest() {
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

    public List<String> getCategoryEntity() {
        return categoryEntity;
    }

    public List<String> getPhotos() {
        return photos;
    }
}
