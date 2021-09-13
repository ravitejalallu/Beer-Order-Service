package com.raviteja.springframwork.BeerOrderService.web.mappers;

import com.raviteja.springframwork.BeerOrderService.domain.BeerOrderLine;
import com.raviteja.springframwork.BeerOrderService.web.model.BeerOrderLineDto;

public class BeerOrderLineMapper {

    public static BeerOrderLine beerOrderLineDtoToBeerOrderLine(BeerOrderLineDto beerOrderLineDto){
        return BeerOrderLine.builder()
                .beerId(beerOrderLineDto.getBeerId())
                .createdDate(DateMapper.asTimestamp(beerOrderLineDto.getCreatedDate()))
                .modifiedDate(DateMapper.asTimestamp(beerOrderLineDto.getLastModifiedDate()))
                .orderQuantity(beerOrderLineDto.getOrderQuantity())
                .version(beerOrderLineDto.getVersion())
                .upc(beerOrderLineDto.getUpc())
                .build();
    }
    public static BeerOrderLineDto beerOrderLineToBeerOrderLineDto(BeerOrderLine beerOrderLine){
        return BeerOrderLineDto.builder()
                .id(beerOrderLine.getId())
                .beerId(beerOrderLine.getBeerId())
                .createdDate(DateMapper.asOffsetDateTime(beerOrderLine.getCreatedDate()))
                .lastModifiedDate(DateMapper.asOffsetDateTime(beerOrderLine.getModifiedDate()))
                .orderQuantity(beerOrderLine.getOrderQuantity())
                .version(beerOrderLine.getVersion())
                .upc(beerOrderLine.getUpc())
                .build();
    }
}
