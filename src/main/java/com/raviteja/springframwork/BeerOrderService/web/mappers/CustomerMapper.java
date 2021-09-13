package com.raviteja.springframwork.BeerOrderService.web.mappers;

import com.raviteja.springframwork.BeerOrderService.domain.Customer;
import com.raviteja.springframwork.BeerOrderService.web.model.CustomerDto;

import java.util.stream.Collectors;

public class CustomerMapper {

    public static Customer customerDtoToCustomer(CustomerDto customerDto){
        return Customer.builder()
                .customerName(customerDto.getCustomerName())
                .apiKey(customerDto.getApiKey())
                .beerOrders(customerDto.getBeerOrders().stream()
                        .map(beerOrderDto -> BeerOrderMapper.BeerOrderDtoToBeerOrder(beerOrderDto))
                         .collect(Collectors.toSet()))
                 .createdDate(DateMapper.asTimestamp(customerDto.getCreatedDate()))
                .modifiedDate(DateMapper.asTimestamp(customerDto.getLastModifiedDate()))
                .version(customerDto.getVersion())
                .build();

    }
    public static CustomerDto customerToCustomerDto(Customer customer){
        return CustomerDto.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .version(customer.getVersion())
                .apiKey(customer.getApiKey())
                .beerOrders(customer.getBeerOrders().stream()
                        .map(beerOrder -> BeerOrderMapper.BeerOrderToBeerOrderDto(beerOrder))
                        .collect(Collectors.toSet()))
                 .createdDate(DateMapper.asOffsetDateTime(customer.getCreatedDate()))
                .lastModifiedDate(DateMapper.asOffsetDateTime(customer.getModifiedDate()))
                .version(customer.getVersion())
                .build();

    }
}
