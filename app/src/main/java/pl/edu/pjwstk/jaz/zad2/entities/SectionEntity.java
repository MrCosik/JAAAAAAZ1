package pl.edu.pjwstk.jaz.zad2.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "section")
public class SectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
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

    public void setId(Long id) {
        this.id = id;
    }

    public void addCategory(String categoryTitle){
        containsCategories.add(new CategoryEntity(categoryTitle));
    }

    public void clearSet(){
        containsCategories.clear();
    }

}
