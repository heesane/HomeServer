package hhs.server.home_server.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix="spring.rabbitmq")
@ConstructorBinding
@AllArgsConstructor
public class RabbitMQConfig {
    private String host;
    private Integer port;
    private String username;
    private String password;
}
