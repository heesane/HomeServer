package hhs.server.common.config;

import hhs.server.common.properties.CustomRabbitMQProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class RabbitMqConfig {

  private final CustomRabbitMQProperties customRabbitMQProperties;

  @Value("${rabbitmq.queue.name}")
  private String queueName;

  @Value("${rabbitmq.exchange.name}")
  private String exchangeName;

  @Value("${rabbitmq.routing.key}")
  private String routingKey;

  @Bean
  public Queue queue() {
    return new Queue(queueName, false);
  }

  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange(exchangeName);
  }

  @Bean
  public Binding binding(Queue queue, DirectExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(routingKey);
  }

  @Bean
  public CachingConnectionFactory connectionFactory() {
    CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
    cachingConnectionFactory.setHost(customRabbitMQProperties.getHost());
    cachingConnectionFactory.setUsername(customRabbitMQProperties.getUsername());
    cachingConnectionFactory.setPassword(customRabbitMQProperties.getPassword());
    cachingConnectionFactory.setPort(customRabbitMQProperties.getPort());
    return cachingConnectionFactory;
  }

  @Bean
  public MessageConverter jackson2JsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
    return rabbitTemplate;
  }
}
