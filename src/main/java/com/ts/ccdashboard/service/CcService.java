package com.ts.ccdashboard.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class CcService {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    private final Map<String, BigDecimal> marketsLatestExchangeRates;
    private final ExternalApiService externalApiService;
    private final List<String> markets;

    private List<CcResponse.CcInfo> ccInfo = new ArrayList<>();
    private String lastChecked;

    public CcService(List<String> markets, ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
        this.marketsLatestExchangeRates = new HashMap<>();
        this.markets = markets;
        calculateData();
    }

    public CcResponse getData() {
        return new CcResponse(lastChecked, ccInfo);
    }

    @Scheduled(fixedRate = 600000)
    public void calculateData() {
        List<CcResponse.CcInfo> info = new ArrayList<>();
        for (String market : markets) {
            float differencePercentage = calculateDifferencePercentage(market);
            if (Math.abs(differencePercentage) > 10.0f) {
                info.add(new CcResponse.CcInfo(market, differencePercentage));
            }
        }
        Collections.sort(info);

        ccInfo = info;
        updateLastChecked();
        log.info("Checked at: {}", lastChecked);
        log.info("Current number of interesting markets: {}", ccInfo.size());
    }

    private float calculateDifferencePercentage(String market) {
        BigDecimal latestExchangeRate = externalApiService.getLatestExchangeRate(market);

        if (!marketsLatestExchangeRates.containsKey(market)) {
            marketsLatestExchangeRates.put(market, latestExchangeRate);
            return 0;
        } else {
            BigDecimal previousExchangeRate = marketsLatestExchangeRates.get(market);
            marketsLatestExchangeRates.put(market, latestExchangeRate);
            return calculateDifferencePercentage(previousExchangeRate, latestExchangeRate);
        }
    }

    private float calculateDifferencePercentage(BigDecimal previousExchangeRate, BigDecimal latestExchangeRate) {
        BigDecimal difference = previousExchangeRate
            .subtract(latestExchangeRate)
            .abs();
        float percentage = difference
            .multiply(BigDecimal.valueOf(100))
            .divide(previousExchangeRate, 2, BigDecimal.ROUND_HALF_UP)
            .floatValue();

        return previousExchangeRate.compareTo(latestExchangeRate) > 0 ? percentage : -percentage;
    }

    private void updateLastChecked() {
        lastChecked = DATE_FORMAT.format(new Date());
    }

}
