package com.raviteja.springframwork.BeerOrderService.repository;

import com.raviteja.springframwork.BeerOrderService.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

   List<Customer> findAllByCustomerNameLike(String name);
}
