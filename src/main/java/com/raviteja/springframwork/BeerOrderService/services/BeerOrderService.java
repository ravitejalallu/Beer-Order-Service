package com.raviteja.springframwork.BeerOrderService.services;

import com.raviteja.springframwork.BeerOrderService.web.model.BeerOrderDto;
import com.raviteja.springframwork.BeerOrderService.web.model.BeerOrderPagedList;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BeerOrderService {
    BeerOrderPagedList listOrders(UUID customerId, Pageable pageable);

    BeerOrderDto getOrderById(UUID customerId, UUID orderId);

    BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto);

    void pickupOrder(UUID customerId, UUID orderId);
}
