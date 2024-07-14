package hhs.server.api.controller;


import hhs.server.api.dto.MessageDto;
import hhs.server.api.service.RabbitMQService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RabbitMQController {
    private final RabbitMQService rabbitMQService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDto messageDto){
        rabbitMQService.sendMessage(messageDto);
        return ResponseEntity.ok("Message sent to the RabbitMQ Successfully");
    }
}
