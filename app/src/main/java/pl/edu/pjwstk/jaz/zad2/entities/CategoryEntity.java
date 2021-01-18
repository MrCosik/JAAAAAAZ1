package pl.edu.pjwstk.jaz.zad2.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "section_id")
    private Long sectionId;
    @Column(name = "title")
    private String title;

    @Transient
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    Set<AuctionEntity> containedInCategories = new HashSet<>();


//    @ManyToMany
//    Set<AuctionEntity> auctions = new HashSet<>();



    public CategoryEntity() {
    }

    public CategoryEntity(String title) {
        this.title = title;
    }

    public CategoryEntity(String title, Long sectionId) {
        this.title = title;
        this.sectionId = sectionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
