package com.ts.ccdashboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class GetTickerResponse {
    private boolean success;
    private String message;
    private Result result;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class Result {
        private BigDecimal Bid;
        private BigDecimal Ask;
        private BigDecimal Last;
    }
}
