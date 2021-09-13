package com.raviteja.springframwork.BeerOrderService.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BeerOrderLine extends BaseEntity{

    @Builder
    public BeerOrderLine(UUID id, Long version, Timestamp createdDate, Timestamp modifiedDate,
                         BeerOrder beerOrder, String upc,UUID beerId, Integer orderQuantity,
                         Integer quantityAllocated) {
        super(id, version, createdDate, modifiedDate);
        this.beerOrder = beerOrder;
        this.upc = upc;
        this.beerId = beerId;
        this.orderQuantity = orderQuantity;
        this.quantityAllocated = quantityAllocated;
    }

    @ManyToOne
    private BeerOrder beerOrder;
    private String upc;
    private UUID beerId;
    private Integer orderQuantity = 0;
    private Integer quantityAllocated = 0;
}
