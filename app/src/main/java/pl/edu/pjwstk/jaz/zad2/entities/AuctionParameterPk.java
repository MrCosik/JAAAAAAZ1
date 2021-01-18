package pl.edu.pjwstk.jaz.zad2.entities;

import javax.persistence.Column;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AuctionParameterPk implements Serializable {

    @Column(name = "auction_id")
    Long auctionId;
    @Column(name = "parameter_id")
    Long parameterId;


    public AuctionParameterPk() {
    }

    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }

    public Long getParameterId() {
        return parameterId;
    }

    public void setParameterId(Long parameterId) {
        this.parameterId = parameterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuctionParameterPk that = (AuctionParameterPk) o;
        return auctionId.equals(that.auctionId) &&
                parameterId.equals(that.parameterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auctionId, parameterId);
    }
}
