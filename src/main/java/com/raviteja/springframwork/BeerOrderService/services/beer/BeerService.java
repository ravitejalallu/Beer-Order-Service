package com.raviteja.springframwork.BeerOrderService.services.beer;

import com.raviteja.springframwork.BeerOrderService.web.model.BeerDto;

import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    Optional<BeerDto> getBeerById(UUID beerId);
    Optional<BeerDto> getBeerByUpc(String upc);
}
