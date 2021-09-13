package com.raviteja.springframwork.BeerOrderService.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Customer  extends BaseEntity{

    @Builder
    public Customer(UUID id, Long version, Timestamp createdDate,
                    Timestamp modifiedDate, String customerName, UUID apiKey,
                    Set<BeerOrder> beerOrders) {
        super(id, version, createdDate, modifiedDate);
        this.customerName = customerName;
        this.apiKey = apiKey;
        this.beerOrders = beerOrders;
    }

    private String customerName;
    @Column(length = 36, columnDefinition = "varchar(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID  apiKey;
    @OneToMany(mappedBy = "customer")
    private Set<BeerOrder> beerOrders;


}
