package com.fan.springcloud.controller;

import com.fan.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class PaymentHystrixController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/consumer/payment/hygtrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_ok(id);
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler"/*指定善后方法名*/, commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    @GetMapping("/consumer/payment/hygtrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息，请稍后重试";
    }


}
