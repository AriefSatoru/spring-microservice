package com.backend.customer_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootTest
@EnableDiscoveryClient //untuk registrasy ke service discovery
class CustomerServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
