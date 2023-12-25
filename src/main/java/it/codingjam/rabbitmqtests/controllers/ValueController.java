package it.codingjam.rabbitmqtests.controllers;

import it.codingjam.rabbitmqtests.Value;
import it.codingjam.rabbitmqtests.events.ValuePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/values", consumes = MediaType.APPLICATION_JSON_VALUE)
public class ValueController {

    private final ValuePublisher valuePublisher;

    @PostMapping("/telemetry")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void publishTelemetry(@RequestBody Value value) {
        valuePublisher.publish("telemetry", value);
    }

    @PostMapping("/metric")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void publishMetric(@RequestBody Value value) {
        valuePublisher.publish("metric", value);
    }
}
