package com.njust.ec.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/showServiceInstancesInfo", method = RequestMethod.GET)
    public String showServiceInstancesInfo() {
        List<ServiceInstance> instances = client.getInstances("client");
        StringBuilder sb = new StringBuilder();
        for (ServiceInstance instance : instances) {
            sb.append("ip:").append(instance.getHost()).append(",port:").append(instance.getPort())
                    .append(",serviceId:").append(instance.getServiceId()).append(",instanceId:")
                    .append(instance.getInstanceId()).append(",uri:").append(instance.getUri()).append("\n\r");
        }
        logger.info(sb.toString());
        return sb.toString();
    }
}
