package com.ts.ccdashboard;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CcInfo implements Comparable<CcInfo> {
    private String id;
    private int value;

    @Override
    public int compareTo(CcInfo o) {
        return Integer.compare(o.value, value);
    }
}
