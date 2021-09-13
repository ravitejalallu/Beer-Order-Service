package com.raviteja.springframwork.BeerOrderService.web.model;

import com.raviteja.springframwork.BeerOrderService.domain.BeerOrder;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends BaseItem{

    @Builder
    public CustomerDto(UUID id, Long version,
                       OffsetDateTime createdDate,
                       OffsetDateTime lastModifiedDate,
                       String customerName,
                       UUID apiKey,
                       Set<BeerOrderDto> beerOrders) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerName = customerName;
        this.apiKey = apiKey;
        this.beerOrders = beerOrders;

    }

    private String customerName;
    private UUID apiKey;
    private Set<BeerOrderDto> beerOrders;

}
