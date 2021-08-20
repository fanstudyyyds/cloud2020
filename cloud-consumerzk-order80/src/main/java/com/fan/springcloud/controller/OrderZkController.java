package com.fan.springcloud.controller;

import com.fan.springcloud.entities.CommonResult;
import com.fan.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderZkController {

    //    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String INVOKE_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/payment/get")
    public String patmentInfo() {
        String forObject = restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
        return forObject;
    }
}
