package it.codingjam.rabbitmqtests.events;

import it.codingjam.rabbitmqtests.Value;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class ValuePublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publish(String destination, Value value) {
        byte[] body = value.text().getBytes(StandardCharsets.UTF_8);
        Message message = new Message(body);
        rabbitTemplate.send("amq.topic", destination, message);
    }
}
