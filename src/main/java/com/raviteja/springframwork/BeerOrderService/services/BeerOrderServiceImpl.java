package com.raviteja.springframwork.BeerOrderService.services;

import com.raviteja.springframwork.BeerOrderService.domain.BeerOrder;
import com.raviteja.springframwork.BeerOrderService.domain.BeerOrderLine;
import com.raviteja.springframwork.BeerOrderService.domain.Customer;
import com.raviteja.springframwork.BeerOrderService.domain.OrderStatusEnum;
import com.raviteja.springframwork.BeerOrderService.repository.BeerOrderLineRepository;
import com.raviteja.springframwork.BeerOrderService.repository.BeerOrderRepository;
import com.raviteja.springframwork.BeerOrderService.repository.CustomerRepository;
import com.raviteja.springframwork.BeerOrderService.services.beer.BeerService;
import com.raviteja.springframwork.BeerOrderService.web.mappers.BeerOrderLineMapper;
import com.raviteja.springframwork.BeerOrderService.web.mappers.BeerOrderMapper;
import com.raviteja.springframwork.BeerOrderService.web.model.BeerDto;
import com.raviteja.springframwork.BeerOrderService.web.model.BeerOrderDto;
import com.raviteja.springframwork.BeerOrderService.web.model.BeerOrderLineDto;
import com.raviteja.springframwork.BeerOrderService.web.model.BeerOrderPagedList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class BeerOrderServiceImpl implements BeerOrderService{

    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderLineRepository beerOrderLineRepository;
    private final CustomerRepository customerRepository;
    private final BeerService beerService;

    public BeerOrderServiceImpl(BeerOrderRepository beerOrderRepository,
                                BeerOrderLineRepository beerOrderLineRepository,
                                CustomerRepository customerRepository, BeerService beerService) {
        this.beerOrderRepository = beerOrderRepository;
        this.beerOrderLineRepository = beerOrderLineRepository;
        this.customerRepository = customerRepository;
        this.beerService = beerService;
    }

    @Override
    public BeerOrderPagedList listOrders(UUID customerId, Pageable pageable) {
        Optional<Customer> customer = customerRepository.findById(customerId);

        if(customer.isPresent()){
            Page<BeerOrder> orders = beerOrderRepository.findAllByCustomer(customer.get(), pageable);


            final List<BeerOrderDto> beers;
            beers = orders.stream()
                    .map(BeerOrderMapper::BeerOrderToBeerOrderDto)
                    .collect(Collectors.toList());

            beers.forEach(beerOrderDto -> beerOrderDto.getBeerOrderLines().forEach(this::enhancedBeerOrderLineDto));


            return new BeerOrderPagedList(
                    beers, PageRequest.of(
                                    orders.getPageable().getPageNumber(),
                    orders.getPageable().getPageSize()),orders.getTotalElements());

        }else {
          return null;
        }

    }


    @Override
    public BeerOrderDto getOrderById(UUID customerId, UUID orderId) {
        BeerOrderDto beerOrderDto = BeerOrderMapper.BeerOrderToBeerOrderDto(getBeerOrderDto(customerId, orderId));
        beerOrderDto.setBeerOrderLines(beerOrderDto.getBeerOrderLines().stream().
                map(this::enhancedBeerOrderLineDto)
                .collect(Collectors.toSet()));
        return beerOrderDto;
    }

    private BeerOrder getBeerOrderDto(UUID customerId, UUID orderId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            Optional<BeerOrder> beerOrderOptional = beerOrderRepository.findById(orderId);
            if (beerOrderOptional.isPresent()) {
                BeerOrder beerOrder = beerOrderOptional.get();
                if (beerOrder.getCustomer().getId().equals(customerId)) {
                    return (beerOrder);
                }

            }
            throw new RuntimeException("Beer Not found");
        }
        throw new RuntimeException("customer not found");
    }

    @Transactional
    @Override
    public BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        BeerOrder savedOrder;

        if(customer.isPresent()){
            BeerOrder beerOrder =BeerOrderMapper.BeerOrderDtoToBeerOrder(beerOrderDto);
            beerOrder.setCustomer(customer.get());
            beerOrder.setOrderStatus(OrderStatusEnum.NEW);
            beerOrder.getBeerOrderLines().forEach(beerOrderLine -> beerOrderLine.setBeerOrder(beerOrder));

            savedOrder = beerOrderRepository.save(beerOrder);
            log.info("Saved Beer Order: " + beerOrder.getId());
            return BeerOrderMapper.BeerOrderToBeerOrderDto(savedOrder);
        }
        throw new RuntimeException("Customer not found");
    }

    @Override
    public void pickupOrder(UUID customerId, UUID orderId) {
        BeerOrder beerOrder = getBeerOrderDto(customerId, orderId);
        beerOrder.setOrderStatus(OrderStatusEnum.PICKED_UP);
        beerOrderRepository.save(beerOrder);
    }

    private BeerOrderLineDto enhancedBeerOrderLineDto(BeerOrderLineDto beerOrderLineDto){
        Optional<BeerDto> beerByUpc = beerService.getBeerByUpc(beerOrderLineDto.getUpc());
        if(beerByUpc.isPresent()) {
            beerOrderLineDto.setBeerName(beerByUpc.get().getBeerName());
            beerOrderLineDto.setBeerStyle(beerByUpc.get().getBeerStyle());
            beerOrderLineDto.setBeerId(beerByUpc.get().getId());
        }
       return beerOrderLineDto;
    }

}
