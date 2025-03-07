package com.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.backend.webclient.CustomerClient;
import com.backend.webclient.ProductClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	// @Bean
	// @LoadBalanced
	// RestTemplate restTemplate() {
	// 	return new RestTemplate();
	// }

	@Autowired
	private LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;

	@Autowired
	private WebClient.Builder wBuilderCustomer;

	@Autowired
	private WebClient.Builder wBuilderProduct;

	@Bean
	WebClient webClientCustomer() {
		return wBuilderCustomer
			.baseUrl("http://customer-service")
			.filter(loadBalancedExchangeFilterFunction)
			.build();
	}

	@Bean
	WebClient webClientProduct() {
		return wBuilderProduct
			.baseUrl("http://product-service")
			.filter(loadBalancedExchangeFilterFunction)
			.build();
	}

	@Bean
	CustomerClient customerClient() {
		HttpServiceProxyFactory factory = HttpServiceProxyFactory
		.builderFor(WebClientAdapter.create(webClientCustomer())).build();

		return factory.createClient(CustomerClient.class);
	}

	@Bean
	ProductClient productClient() {
		HttpServiceProxyFactory factory = HttpServiceProxyFactory
		.builderFor(WebClientAdapter.create(webClientProduct())).build();

		return factory.createClient(ProductClient.class);
	}
}
