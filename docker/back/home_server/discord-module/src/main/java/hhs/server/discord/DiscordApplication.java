package hhs.server.discord;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class DiscordApplication {
    public static void main(String[] args) {
        MDC.put("moduleName","DISCORD");
        log.info("Starting Discord module");
        SpringApplication.run(DiscordApplication.class, args);
    }
}
