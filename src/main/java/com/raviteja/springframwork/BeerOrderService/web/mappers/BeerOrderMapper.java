package com.raviteja.springframwork.BeerOrderService.web.mappers;

import com.raviteja.springframwork.BeerOrderService.domain.BeerOrder;
import com.raviteja.springframwork.BeerOrderService.domain.Customer;
import com.raviteja.springframwork.BeerOrderService.web.model.BeerOrderDto;
import com.raviteja.springframwork.BeerOrderService.web.model.OrderStatusEnum;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

public class BeerOrderMapper {

    public static BeerOrder BeerOrderDtoToBeerOrder(BeerOrderDto beerOrderDto){
        return BeerOrder.builder()
                .customerRef(beerOrderDto.getCustomerRef())
                .createdDate(DateMapper.asTimestamp(beerOrderDto.getCreatedDate()))
                .modifiedDate(DateMapper.asTimestamp((beerOrderDto.getLastModifiedDate())))
                .orderStatusCallbackUrl(beerOrderDto.getOrderStatusCallbackUrl())
                .customer(Customer.builder().id(beerOrderDto.getCustomerId()).build())
                .beerOrderLines(beerOrderDto.getBeerOrderLines()
                        .stream()
                        .map(beerOrderLineDto -> BeerOrderLineMapper.beerOrderLineDtoToBeerOrderLine(beerOrderLineDto))
                .collect(Collectors.toSet()))
               .build();


    }

    public static BeerOrderDto BeerOrderToBeerOrderDto(BeerOrder beerOrder){
        return  BeerOrderDto.builder()
                .id(beerOrder.getId())
                .version(beerOrder.getVersion())
                .createdDate(DateMapper.asOffsetDateTime(beerOrder.getCreatedDate()))
                .lastModifiedDate(DateMapper.asOffsetDateTime(beerOrder.getModifiedDate()))
                .customerId(beerOrder.getCustomer().getId())
                .customerRef(beerOrder.getCustomerRef())
                .orderStatus(OrderStatusEnum.valueOf(beerOrder.getOrderStatus().name()))
                .orderStatusCallbackUrl(beerOrder.getOrderStatusCallbackUrl())
                .beerOrderLines(beerOrder.getBeerOrderLines()
                        .stream()
                        .map(beerOrderLine -> BeerOrderLineMapper.beerOrderLineToBeerOrderLineDto(beerOrderLine))
                        .collect(Collectors.toSet()))
                .build();

    }
}
