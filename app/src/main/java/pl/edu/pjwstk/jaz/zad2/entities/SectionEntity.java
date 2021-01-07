package pl.edu.pjwstk.jaz.zad2.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "section")
public class SectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;

    @OneToMany
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    Set<CategoryEntity> containsCategories = new HashSet<>();


    public SectionEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addCategory(String categoryTitle){
        containsCategories.add(new CategoryEntity(categoryTitle));
    }
}
