package com.ts.ccdashboard.service.bittrex;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
class GetTickerResponse {
    private boolean success;
    private String message;
    private Result result;

    @Getter
    @Setter
    static class Result {
        private BigDecimal Bid;
        private BigDecimal Ask;
        private BigDecimal Last;
    }
}
