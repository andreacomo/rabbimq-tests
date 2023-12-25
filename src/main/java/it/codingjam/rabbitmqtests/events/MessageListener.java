package it.codingjam.rabbitmqtests.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListener {

    @RabbitListener(queues = {"telemetry-queue", "metric-queue"})
    public void receive(Message message) {
        log.info(message.toString());
    }
}
