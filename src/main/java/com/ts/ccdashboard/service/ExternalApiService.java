package com.ts.ccdashboard.service;

import java.math.BigDecimal;

public interface ExternalApiService {

    BigDecimal getLatestExchangeRate(String market);

}
