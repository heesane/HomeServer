package hhs.server.common.config;

import org.springframework.context.annotation.Configuration;

@EnableJpaRepositories(basePackages = "hhs.server.domain.repository.jpa")
@Configuration
public class JpaConfig {

}
