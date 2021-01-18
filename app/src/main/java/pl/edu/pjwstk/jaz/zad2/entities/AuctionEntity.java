package pl.edu.pjwstk.jaz.zad2.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "auction")
public class AuctionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "created_by")
    private Long creatorsId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private int price;


    @Transient
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "auction_id", referencedColumnName = "id")
    private final Set<PhotoEntity> photos = new HashSet<>();

    @OneToMany(
            mappedBy = "auctionEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<AuctionParameterEntity> parameterValue;





    public AuctionEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

//    public void addCategory(String categoryName) {
//        containedInCategories.add(new CategoryEntity(categoryName));
//    }

    public void addPhoto(String photoTitle, int photoPosition) {
        photos.add(new PhotoEntity(photoTitle, photoPosition));
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getCreatorsId() {
        return creatorsId;
    }

    public void setCreatorsId(Long creatorsId) {
        this.creatorsId = creatorsId;
    }
}
