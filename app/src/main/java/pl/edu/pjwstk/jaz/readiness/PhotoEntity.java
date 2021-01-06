package pl.edu.pjwstk.jaz.readiness;

import javax.persistence.*;

@Entity(name = "photo")
public class PhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String name;
    @Column(name = "size")
    private float size;
    @Column(name = "auction_id")
    private int auction_fk;

    public PhotoEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getAuction_fk() {
        return auction_fk;
    }

    public void setAuction_fk(int auction_fk) {
        this.auction_fk = auction_fk;
    }
}
