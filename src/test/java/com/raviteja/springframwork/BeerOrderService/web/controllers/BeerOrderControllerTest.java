package com.raviteja.springframwork.BeerOrderService.web.controllers;

import com.raviteja.springframwork.BeerOrderService.services.BeerOrderService;
import com.raviteja.springframwork.BeerOrderService.web.model.BeerOrderDto;
import com.raviteja.springframwork.BeerOrderService.web.model.BeerOrderPagedList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class BeerOrderControllerTest {

    @MockBean
    BeerOrderService beerOrderService;
    @Autowired
    MockMvc mockMvc;

    @Test
    void getBeerOrders() throws Exception {
        BeerOrderDto beerOrderDto1 =  BeerOrderDto.builder().id(UUID.randomUUID()).build();
        BeerOrderDto beerOrderDto2 =  BeerOrderDto.builder().id(UUID.randomUUID()).build();
        List<BeerOrderDto> beerOrderDtoList = Arrays.asList(beerOrderDto1,beerOrderDto2);
        BeerOrderPagedList beerOrderPagedList = new BeerOrderPagedList(beerOrderDtoList);
        Integer pageNumber = 1;
        Integer pageSize = 25;
        when(beerOrderService.listOrders(UUID.randomUUID(), PageRequest.of(pageNumber,pageSize))).thenReturn(beerOrderPagedList);
        mockMvc.perform(get("/api/v1/customers/"+UUID.randomUUID().toString()+"/orders")
                .param("pageNumber", String.valueOf(pageNumber))
                .param("pageSize",String.valueOf(pageSize)))
                .andExpect(status().isOk());

    }
}