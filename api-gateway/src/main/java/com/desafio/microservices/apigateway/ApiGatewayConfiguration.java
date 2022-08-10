package com.desafio.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p.path("/pedidos/**")
                        .filters(filter -> filter
                                .addRequestHeader("MyAtt", "from gateway" )
                        )
                        .uri("lb://pedidos/")
                )

                .route(p -> p.path("/api/pagamentos/**")
                        .filters(filter -> filter
                                .addRequestHeader("Redirecionado", "true" )
                                .addRequestHeader("MyAtt", "from gateway" )
                        )
                        .uri("lb://pagamentos")
                )
                .build();
    }
}
