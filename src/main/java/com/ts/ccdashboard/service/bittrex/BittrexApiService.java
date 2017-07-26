package com.ts.ccdashboard.service.bittrex;

import com.google.gson.Gson;
import com.ts.ccdashboard.service.ExternalApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class BittrexApiService implements ExternalApiService {

    private static final String API_URL = "https://bittrex.com/api/v1.1/public/getticker?market=";
    private final Gson gson;
    private final RestTemplate restTemplate;

    public BittrexApiService(Gson gson, RestTemplate restTemplate) {
        this.gson = gson;
        this.restTemplate = restTemplate;
    }

    @Override
    public BigDecimal getLatestExchangeRate(String market) {
        ResponseEntity<String> response = restTemplate.getForEntity(API_URL + market, String.class);
        return gson.fromJson(response.getBody(), GetTickerResponse.class).getResult().getLast();
    }
}
