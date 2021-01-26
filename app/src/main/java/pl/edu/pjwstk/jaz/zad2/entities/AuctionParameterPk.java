package pl.edu.pjwstk.jaz.zad2.entities;

import javax.persistence.Column;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AuctionParameterPk implements Serializable {

    Long auctionId;
    Long parameterId;


    public AuctionParameterPk(Long auctionId, Long parameterId) {
        this.auctionId = auctionId;
        this.parameterId = parameterId;
    }
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
        return Objects.equals(auctionId, that.auctionId) && Objects.equals(parameterId, that.parameterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auctionId, parameterId);
    }
}
