package com.raviteja.springframwork.BeerOrderService.web.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerOrderDto extends BaseItem{

    @Builder
    public BeerOrderDto(UUID id, Long version, OffsetDateTime createdDate,
                        OffsetDateTime lastModifiedDate, UUID customerId,
                        String customerRef, Set<BeerOrderLineDto> beerOrderLines,
                        OrderStatusEnum orderStatus, String orderStatusCallbackUrl) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerId = customerId;
        this.customerRef = customerRef;
        this.beerOrderLines = beerOrderLines;
        this.orderStatus = orderStatus;
        this.orderStatusCallbackUrl = orderStatusCallbackUrl;
    }
    @Null
    private UUID customerId;
    private String customerRef;
    private Set<BeerOrderLineDto> beerOrderLines;
    private OrderStatusEnum orderStatus;
    private String orderStatusCallbackUrl;
}
