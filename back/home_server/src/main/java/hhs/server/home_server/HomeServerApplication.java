package hhs.server.home_server;

import hhs.server.home_server.config.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CorsConfig.class)
public class HomeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeServerApplication.class, args);
	}

}
