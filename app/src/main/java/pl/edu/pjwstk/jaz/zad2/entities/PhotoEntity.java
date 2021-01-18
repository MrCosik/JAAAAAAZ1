package pl.edu.pjwstk.jaz.zad2.entities;

import javax.persistence.*;

@Entity
@Table(name = "photo")
public class PhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "auction_id")
    private int auctionId;
    @Column(name = "link")
    private String link;
    @Column(name = "position")
    private int position;

    public PhotoEntity() {
    }

    public PhotoEntity(String link, int position){
        this.link = link;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
