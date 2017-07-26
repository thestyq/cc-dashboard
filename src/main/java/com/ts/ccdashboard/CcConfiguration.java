package com.ts.ccdashboard;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CcConfiguration {

    @Bean
    public MarketsProvider marketsProvider() {
        return new MarketsProvider();
    }

    @Bean
    public ApiService apiService() {
        return new ApiService(new Gson(), new RestTemplate());
    }

    @Bean
    public CcService ccService(MarketsProvider marketsProvider, ApiService apiService) {
        return new CcService(marketsProvider.loadMarkets(), apiService);
    }

}
