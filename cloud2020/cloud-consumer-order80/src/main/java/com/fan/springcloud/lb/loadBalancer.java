package com.fan.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface loadBalancer {

    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
