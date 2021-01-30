package pl.edu.pjwstk.jaz.zad2.entities;

public class ShowAuctionEntity {

    private Long id;
    private String category;
    private String title;
    private String description;
    private String miniaturePhotoLink;

    public ShowAuctionEntity() {
    }

    public ShowAuctionEntity(Long id, String category, String title, String description, String miniaturePhotoLink) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.miniaturePhotoLink = miniaturePhotoLink;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getMiniaturePhotoLink() {
        return miniaturePhotoLink;
    }

    public void setMiniaturePhotoLink(String miniaturePhotoLink) {
        this.miniaturePhotoLink = miniaturePhotoLink;
    }
}
