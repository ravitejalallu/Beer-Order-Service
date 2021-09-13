package com.raviteja.springframwork.BeerOrderService.repository;

import com.raviteja.springframwork.BeerOrderService.domain.BeerOrder;
import com.raviteja.springframwork.BeerOrderService.domain.Customer;
import com.raviteja.springframwork.BeerOrderService.domain.OrderStatusEnum;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.UUID;

public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {

    Page<BeerOrder> findAllByCustomer(Customer customer , Pageable pageable);

    List<BeerOrder> findAllByOrderStatus(OrderStatusEnum orderStatusEnum);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    BeerOrder findOneById(UUID id);
}
