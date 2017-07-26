package com.ts.ccdashboard;

import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ApiService {

    private static final String API_URL = "https://bittrex.com/api/v1.1/public/getticker?market=";
    private final Gson gson;
    private final RestTemplate restTemplate;

    public ApiService(Gson gson, RestTemplate restTemplate) {
        this.gson = gson;
        this.restTemplate = restTemplate;
    }

    public GetTickerResponse getTicker(String market) {
        ResponseEntity<String> response = restTemplate.getForEntity(API_URL + market, String.class);
        return gson.fromJson(response.getBody(), GetTickerResponse.class);
    }
}
