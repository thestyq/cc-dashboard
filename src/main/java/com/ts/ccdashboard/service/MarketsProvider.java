package com.ts.ccdashboard.service;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MarketsProvider {

    public List<String> loadBittrexMarkets() {
        try (InputStream marketsInputStream = getClass().getResourceAsStream("/bittrexMarkets.txt")) {
            return IOUtils.readLines(marketsInputStream, "UTF-8");
        } catch (IOException e) {
            throw new IllegalStateException("Can not find or read bittrex markets file");
        }
    }
}
