package com.raviteja.springframwork.BeerOrderService.repository;

import com.raviteja.springframwork.BeerOrderService.domain.BeerOrderLine;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerOrderLineRepository extends PagingAndSortingRepository<BeerOrderLine, UUID> {
}
