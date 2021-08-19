package com.fan.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
//自定义负载均衡
//@Component
public class MyLb implements loadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAtomicInteger() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("*******next" + next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAtomicInteger() % serviceInstances.size();

        return serviceInstances.get(index);
    }
}
