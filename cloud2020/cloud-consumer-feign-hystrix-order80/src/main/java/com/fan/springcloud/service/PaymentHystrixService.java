package com.fan.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {
    @GetMapping("/payment/hygtrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id);
    @GetMapping("/payment/hygtrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id")Integer id);


}
