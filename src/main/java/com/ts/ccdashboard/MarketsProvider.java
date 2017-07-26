package com.ts.ccdashboard;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MarketsProvider {

    public List<String> loadMarkets() {
        try (InputStream marketsInputStream = getClass().getResourceAsStream("/markets.txt")) {
            return IOUtils.readLines(marketsInputStream, "UTF-8");
        } catch (IOException e) {
            throw new IllegalStateException("Can not find or read markets file");
        }
    }
}
