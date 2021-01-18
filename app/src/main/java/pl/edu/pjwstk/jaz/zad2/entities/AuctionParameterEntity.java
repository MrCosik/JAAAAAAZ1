package pl.edu.pjwstk.jaz.zad2.entities;

import javax.persistence.*;

@Entity
@Table(name = "auction_parameter")
public class AuctionParameterEntity {

    @EmbeddedId
    AuctionParameterPk auctionParameterPk;

    @ManyToOne
    @MapsId("auctionId")
    @JoinColumn(name = "auction_id")
    AuctionEntity auctionEntity;

    @ManyToOne
    @MapsId("parameterId")
    @JoinColumn(name = "parameter_id")
    ParameterEntity parameterEntity;

    private String value;

    public AuctionParameterEntity() {
    }

    public AuctionParameterPk getAuctionParameterKey() {
        return auctionParameterPk;
    }

    public void setAuctionParameterKey(AuctionParameterPk auctionParameterPk) {
        this.auctionParameterPk = auctionParameterPk;
    }

    public AuctionEntity getAuctionEntity() {
        return auctionEntity;
    }

    public void setAuctionEntity(AuctionEntity auctionEntity) {
        this.auctionEntity = auctionEntity;
    }

    public ParameterEntity getParameterEntity() {
        return parameterEntity;
    }

    public void setParameterEntity(ParameterEntity parameterEntity) {
        this.parameterEntity = parameterEntity;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
