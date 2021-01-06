package pl.edu.pjwstk.jaz.readiness;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "auction")
public class AuctionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "created_by")
    private Long creatorsId;


    @Transient
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "auction_id", referencedColumnName = "id")
    private Set<PhotoEntity> photos = new HashSet();

    @ManyToMany
    @JoinTable(
            name = "auction_category",
            joinColumns = @JoinColumn(name = "auction_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<CategoryEntity> containedInCategories = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "auction_parameter",
            joinColumns = @JoinColumn(name = "auction_id"),
            inverseJoinColumns = @JoinColumn(name = "parameter_id")
    )
    Set<ParameterEntity> parameters = new HashSet<>();

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
}
