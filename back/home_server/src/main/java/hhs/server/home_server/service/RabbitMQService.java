package hhs.server.home_server.service;

import hhs.server.home_server.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQService {
    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(MessageDto messageDto){
        log.info("message send : {}",messageDto.getMessage());
        this.rabbitTemplate.convertAndSend(exchangeName,routingKey,messageDto);
    }

    @RabbitListener(queues= "${rabbitmq.queue.name}")
    public void receiveMessage(MessageDto messageDto){
        log.info("message received : {}",messageDto.toString());
    }
}
