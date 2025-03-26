package com.karan.order_service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced  // 👈 This enables service name resolution through Eureka
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
