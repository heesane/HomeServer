package hhs.server.batch;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Slf4j
@EntityScan(basePackages = {"hhs.server.domain", "hhs.server.common"})
@SpringBootApplication(scanBasePackages = "hhs.server")
public class BatchApplication {
    public static void main(String[] args) {
        MDC.put("moduleName","BATCH");
        log.info("Starting Batch module");
        SpringApplication.run(BatchApplication.class, args);
    }
}
