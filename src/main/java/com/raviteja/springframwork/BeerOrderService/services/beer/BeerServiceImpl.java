package com.raviteja.springframwork.BeerOrderService.services.beer;

import com.raviteja.springframwork.BeerOrderService.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class BeerServiceImpl implements BeerService{

    private final RestTemplate restTemplate;

    @Value("${ravi.brewery.apihost}")
    public String apihost;

    public static final String BEER_ID_PATH="/api/v1/beer/{beerId}";
    public static final String BEER_UPC_PATH="/api/v1/beerUpc/{upc}";

    public BeerServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Optional<BeerDto> getBeerById(UUID beerId) {

        final ResponseEntity<Optional<BeerDto>> exchange = restTemplate.exchange(apihost + BEER_ID_PATH, HttpMethod.GET, null,
                new ParameterizedTypeReference<Optional<BeerDto>>() {
                }, beerId);

        return Optional.of(Objects.requireNonNull(exchange.getBody()).get());
    }

    @Override
    public Optional<BeerDto> getBeerByUpc(String upc) {
        final ResponseEntity<Optional<BeerDto>> exchange = restTemplate.exchange(apihost + BEER_UPC_PATH, HttpMethod.GET, null,
                new ParameterizedTypeReference<Optional<BeerDto>>() {
                }, upc);
        return Optional.of(Objects.requireNonNull(exchange.getBody()).get());
    }
}
