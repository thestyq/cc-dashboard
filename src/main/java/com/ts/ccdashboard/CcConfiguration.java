package com.ts.ccdashboard;

import com.google.gson.Gson;
import com.ts.ccdashboard.service.CcService;
import com.ts.ccdashboard.service.ExternalApiService;
import com.ts.ccdashboard.service.MarketsProvider;
import com.ts.ccdashboard.service.bittrex.BittrexApiService;
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
    public ExternalApiService bittrexApiService() {
        return new BittrexApiService(new Gson(), new RestTemplate());
    }

    @Bean
    public CcService bittrexCcService(MarketsProvider marketsProvider, ExternalApiService apiService) {
        return new CcService(marketsProvider.loadBittrexMarkets(), apiService);
    }

}
