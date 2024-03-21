package hhs.server.home_server.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix="spring.rabbitmq")
@AllArgsConstructor
public class RabbitMQProperties {
    private String host;
    private Integer port;
    private String username;
    private String password;
}
