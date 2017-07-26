package com.ts.ccdashboard;

import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CcService {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    private final Map<String, BigDecimal> marketsLastValues;
    private final ApiService apiService;
    private final List<String> markets;

    private List<CcResponse.CcInfo> ccInfo = new ArrayList<>();
    private String lastChecked;

    public CcService(List<String> markets, ApiService apiService) {
        this.apiService = apiService;
        this.marketsLastValues = new HashMap<>();
        this.markets = markets;
        calculateData();
    }

    public CcResponse getData() {
        return new CcResponse(lastChecked, ccInfo);
    }

    @Scheduled(fixedRate = 60000)
    public void calculateData() {
        List<CcResponse.CcInfo> info = new ArrayList<>();
        for (String market : markets) {
            float differencePercentage = calculateDifferencePercentage(market);
            if (Math.abs(differencePercentage) > 1) {
                info.add(new CcResponse.CcInfo(market, differencePercentage));
            }
        }
        Collections.sort(info);

        ccInfo = info;
        updateLastChecked();
        System.out.println("Last checked: " + lastChecked);
        System.out.println("Size: " + ccInfo.size());
    }

    private float calculateDifferencePercentage(String market) {
        GetTickerResponse getTickerResponse = apiService.getTicker(market);

        BigDecimal last = getTickerResponse.getResult().getLast();
        if (!marketsLastValues.containsKey(market)) {
            marketsLastValues.put(market, last);
            return 0;
        } else {
            BigDecimal previousLast = marketsLastValues.get(market);
            marketsLastValues.put(market, last);
            return calculateDifferencePercentage(last, previousLast);
        }
    }

    private float calculateDifferencePercentage(BigDecimal last, BigDecimal previousLast) {
        BigDecimal difference = previousLast.subtract(last).abs();
        float percentage = difference.multiply(BigDecimal.valueOf(100)).divide(previousLast, 2, BigDecimal.ROUND_HALF_UP).floatValue();

        return previousLast.compareTo(last) > 0 ? percentage : -percentage;
    }

    private void updateLastChecked() {
        lastChecked = DATE_FORMAT.format(new Date());
    }

}
