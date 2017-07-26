package com.ts.ccdashboard;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class CcService {
    public List<CcInfo> getCcData() {
        Random random = new Random();
        List<CcInfo> info = new ArrayList<>();
        info.add(new CcInfo("A", random.nextInt()));
        info.add(new CcInfo("B", random.nextInt()));
        info.add(new CcInfo("C", random.nextInt()));
        info.add(new CcInfo("D", random.nextInt()));
        info.add(new CcInfo("R", random.nextInt()));
        Collections.sort(info);
        return info;
    }
}
