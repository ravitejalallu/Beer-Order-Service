package com.raviteja.springframwork.BeerOrderService.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class BeerOrder extends BaseEntity{

    private String customerRef;

    @Builder
    public BeerOrder(UUID id, Long version, Timestamp createdDate, Timestamp modifiedDate,
                     String customerRef, Customer customer, Set<BeerOrderLine> beerOrderLines,
                     OrderStatusEnum orderStatus,
                     String orderStatusCallbackUrl) {
        super(id, version, createdDate, modifiedDate);
        this.customerRef = customerRef;
        this.customer = customer;
        this.beerOrderLines = beerOrderLines;
        this.orderStatus  = orderStatus;
        this.orderStatusCallbackUrl = orderStatusCallbackUrl;
    }

    @ManyToOne()
    private Customer customer;
    @OneToMany(mappedBy = "beerOrder", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<BeerOrderLine> beerOrderLines;
    private String orderStatusCallbackUrl;
    private OrderStatusEnum orderStatus = OrderStatusEnum.NEW;
}
