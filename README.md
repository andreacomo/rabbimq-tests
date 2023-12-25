# RabbitMQ persistency

Try to understand how mqtt messages are persisted when consumer is not online

You need first:
* [MQTTX](https://mqttx.app/) (MQTT client)
* Start Spring Boot app
* Send these command while MQTT client is online, then offline, then reconnect it to receive messages
```
curl http://localhost:8080/values/telemetry -X POST -H 'Content-Type: application/json' -d '{"text": "11"}' -v
curl http://localhost:8080/values/metric -X POST -H 'Content-Type: application/json' -d '{"text": "11"}' -v
```

>**OUTCOME** -> the MQTT client should have "clean session" set to `false` and should subscribe in `OoS1`. 
>
> For publishing, theoretically should be `QoS1` as well, but it looks like `QoS0` works as well because AMQP is subscribed with a durable queue
>
> Publishing from AMQP with header `x-mqtt-publish-qos=1` breaks the MQTT on subscription for some reason -> no need to set it because actually only subscription level matters
 
See https://www.rabbitmq.com/mqtt.html#durability

# Native compilation

Build native image:

```shell
mvn clean package spring-boot:build-image -Pnative 
```
See https://github.com/dashaun/paketo-arm64 for building on ARM

Run for ARM64 with 

```shell
docker run --rm --platform linux/arm64 -p 8080:8080 -e SPRING_RABBITMQ_HOST=host.docker.internal rabbitmq-tests:0.0.1-SNAPSHOT
```