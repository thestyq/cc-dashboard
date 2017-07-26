package com.ts.ccdashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CcController {

    @Autowired
    private CcService ccService;

    @RequestMapping("/data")
    public CcResponse getData() {
        return ccService.getData();
    }
}
