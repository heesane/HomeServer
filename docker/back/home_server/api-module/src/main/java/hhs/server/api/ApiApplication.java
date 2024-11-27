package hhs.server.api;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@ComponentScan(basePackages = "hhs.server")
@EntityScan(basePackages = "hhs.server.domain")
@EnableJpaRepositories(basePackages = "hhs.server.domain.repository.jpa")
@SpringBootApplication
public class ApiApplication {
    public static void main(String[] args) {
        MDC.put("moduleName","API");
        log.info("Starting API module");
        SpringApplication.run(ApiApplication.class, args);
    }
}
