package com.desafio.microservicos.pagamentos.pagamentos.netflix.ribbon;
import feign.Client;

public class LoadBalancerFeignClient {
    public LoadBalancerFeignClient(Client delegate, CachingSpringLoadBalancerFactory lbClientFactory, SpringClientFactory clientFactory) {
        throw new UnsupportedOperationException();
    }

    public Client getDelegate() {
        throw new UnsupportedOperationException();
    }
}
