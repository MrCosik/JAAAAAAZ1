package pl.edu.pjwstk.jaz.zad2.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parameter")
public class ParameterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "value")
    private String value;

    @OneToMany(
            mappedBy = "parameterEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<AuctionParameterEntity> parameterValue;

    public ParameterEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<AuctionParameterEntity> getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(Set<AuctionParameterEntity> parameterValue) {
        this.parameterValue = parameterValue;
    }
}
