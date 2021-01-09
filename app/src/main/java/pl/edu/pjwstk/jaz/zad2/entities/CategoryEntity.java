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
    private Long Id;
    @Column(name = "title")
    private String title;
    @Column(name = "section_id")
    private Long sectionId;

    @ManyToMany
    Set<AuctionEntity> auctions = new HashSet<>();



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
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }


}
