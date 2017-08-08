package xframe.springcloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import com.netflix.hystrix.HystrixCommandProperties;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

/**
 * 使用@EnableCircuitBreaker注解开启断路器功能
 * @author 
 */



@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
public class UserApplication {
	/*
	HystrixCommandProperties.Setter commandProperties=HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true)
			.withCircuitBreakerForceOpen(false)
			.withCircuitBreakerForceClosed(false)
			.withCircuitBreakerErrorThresholdPercentage(50)
			.withCircuitBreakerRequestVolumeThreshold(20)
			.withCircuitBreakerSleepWindowInMilliseconds(5000);
			*/
	public static void main(String[] args) {
		new SpringApplicationBuilder(UserApplication.class).web(true).run(args);
	}
}
