package pl.edu.pjwstk.jaz.zad2.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "parameter")
public class ParameterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "key")
    private String key;

    @OneToMany(
            mappedBy = "parameterEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<AuctionParameterEntity> parameterValue = new HashSet<>();

    public ParameterEntity() {
    }

    public ParameterEntity(String key) {
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return key;
    }

    public void setValue(String key) {
        this.key = key;
    }

    public Set<AuctionParameterEntity> getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(Set<AuctionParameterEntity> parameterValue) {
        this.parameterValue = parameterValue;
    }
}
