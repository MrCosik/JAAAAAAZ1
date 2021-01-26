package pl.edu.pjwstk.jaz.zad2.request;

import pl.edu.pjwstk.jaz.zad2.entities.CategoryEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuctionRequest {

    private String title;
    private String description;
    private String category;
    private Long price;
    private List<String> photos;
    private Map<String,String> parameters = new HashMap<>();


    public AuctionRequest(String title, String description, String category, Long price, List<String> photos, Map<String, String> parameters) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.photos = photos;
        this.parameters = parameters;
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


    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}
