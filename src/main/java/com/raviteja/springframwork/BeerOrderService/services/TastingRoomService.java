package com.raviteja.springframwork.BeerOrderService.services;

import com.raviteja.springframwork.BeerOrderService.bootstrap.BeerOrderBootstrap;
import com.raviteja.springframwork.BeerOrderService.domain.Customer;
import com.raviteja.springframwork.BeerOrderService.repository.BeerOrderRepository;
import com.raviteja.springframwork.BeerOrderService.repository.CustomerRepository;
import com.raviteja.springframwork.BeerOrderService.web.model.BeerOrderDto;
import com.raviteja.springframwork.BeerOrderService.web.model.BeerOrderLineDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class TastingRoomService {

    private final CustomerRepository customerRepository;
    private final BeerOrderService beerOrderService;
    private final BeerOrderRepository beerOrderRepository;

    private final List<String> beerUpcs = new ArrayList<>(3);

    public TastingRoomService(CustomerRepository customerRepository,
                              BeerOrderService beerOrderService,
                              BeerOrderRepository beerOrderRepository) {
        this.customerRepository = customerRepository;
        this.beerOrderService = beerOrderService;
        this.beerOrderRepository = beerOrderRepository;
        beerUpcs.add(BeerOrderBootstrap.BEER_1_UPC);
        beerUpcs.add(BeerOrderBootstrap.BEER_2_UPC);
        beerUpcs.add(BeerOrderBootstrap.BEER_3_UPC);
    }

    @Transactional
    @Scheduled(fixedRate = 10000)
    public void placeTastingRoomOrders(){
        List<Customer> allByCustomers = customerRepository.findAllByCustomerNameLike(BeerOrderBootstrap.TASTING_ROOM);

        if(allByCustomers.size()==1){
            doPlaceOrder(allByCustomers.get(0));
        }else{
            log.error("Too many or too few tasting room customers found");
        }

    }

    private void doPlaceOrder(Customer customer) {
        String beerToOrder = getRandomBeerUpc();

        BeerOrderLineDto beerOrderLine = BeerOrderLineDto.builder()
                .upc(beerToOrder)
                .orderQuantity(new Random().nextInt(6)) //todo externalize value to property
                .build();

        Set<BeerOrderLineDto> beerOrderLineSet = new HashSet<>();
        beerOrderLineSet.add(beerOrderLine);

        BeerOrderDto beerOrderDto = BeerOrderDto.builder()
                .customerId(customer.getId())
                .customerRef(UUID.randomUUID().toString())
                .beerOrderLines(beerOrderLineSet)
                .build();

        beerOrderService.placeOrder(customer.getId(), beerOrderDto);

    }

    private String getRandomBeerUpc() {
        return beerUpcs.get(new Random().nextInt(beerUpcs.size() -0));
    }
}
