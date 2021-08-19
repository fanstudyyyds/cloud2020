package com.fan.springcloud.service;

import com.fan.springcloud.entities.CommonResult;
import com.fan.springcloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @RequestMapping("/payment/get/{id}")
    public CommonResult paymentById(@PathVariable("id") Long id);

    //超时处理
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();
}
