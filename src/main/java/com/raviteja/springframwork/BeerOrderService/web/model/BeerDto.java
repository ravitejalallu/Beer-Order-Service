package com.raviteja.springframwork.BeerOrderService.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto  {

    @Null
    private UUID id;
    @Null
    private Long version;
    @NotBlank
    private String beerName;
    @NotNull
    private String beerStyle;
    private String upc;
    @Positive
    @NotNull
    private BigDecimal price;
    @Positive
    @NotNull
    private Integer quantityOnHand;

    private Integer quantityToBrew;

    @Null
    private OffsetDateTime createdDate;
    @Null
    private OffsetDateTime lastModifiedDate;
}
