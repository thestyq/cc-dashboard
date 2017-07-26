package com.ts.ccdashboard.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CcResponse {
    private String lastChecked;
    private List<CcInfo> ccInfo;

    @Getter
    @AllArgsConstructor
    public static class CcInfo implements Comparable<CcInfo>{
        private String id;
        private float value;

        @Override
        public int compareTo(CcInfo o) {
            return Float.compare(o.value, value);
        }
    }
}
