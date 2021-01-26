package pl.edu.pjwstk.jaz.zad2.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "auction_parameter")
//@IdClass(AuctionParameterPk.class)
public class AuctionParameterEntity {

    @EmbeddedId
    AuctionParameterPk auctionParameterPk;

    @ManyToOne
    @MapsId(value = "auctionId")
    @JoinColumn(name = "auction_id")
    AuctionEntity auctionEntity;

    @ManyToOne
    @MapsId(value = "parameterId")
    @JoinColumn(name = "parameter_id")
    ParameterEntity parameterEntity;

    @Column(name = "value")
    private String value;

    public AuctionParameterEntity() {
    }

    public AuctionParameterEntity(AuctionEntity auctionEntity, ParameterEntity parameterEntity, String value) {
        this.auctionEntity = auctionEntity;
        this.parameterEntity = parameterEntity;
        this.value = value;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuctionParameterEntity that = (AuctionParameterEntity) o;
        return Objects.equals(auctionParameterPk, that.auctionParameterPk) && Objects.equals(auctionEntity, that.auctionEntity) && Objects.equals(parameterEntity, that.parameterEntity) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auctionParameterPk, auctionEntity, parameterEntity, value);
    }
}
