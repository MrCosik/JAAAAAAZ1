package pl.edu.pjwstk.jaz.zad2.request;

import pl.edu.pjwstk.jaz.zad2.entities.CategoryEntity;

import java.util.List;

public class AuctionRequest {

    private String title;
    private String description;
    private String category;
    private Long price;
    private List<String> photos;


    public AuctionRequest(String title, String description, String category, List<String> photos, Long price) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.photos = photos;
        this.price = price;
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

    public String getCategory() {
        return category;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
