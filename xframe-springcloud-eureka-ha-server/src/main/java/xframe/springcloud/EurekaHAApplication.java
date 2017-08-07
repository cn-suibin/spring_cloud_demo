package xframe.springcloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaHAApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EurekaHAApplication.class).web(true).run(args);
	}

}