package com.mai.solar.energyControl.managers;

import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;
import com.hivemq.client.mqtt.mqtt3.message.publish.Mqtt3Publish;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class HiveMqConnectionManager {

    private final Mqtt3AsyncClient mqtt3Client;

    public HiveMqConnectionManager(Mqtt3AsyncClient mqtt3Client) {
        this.mqtt3Client = mqtt3Client;
    }

    @PostConstruct
    public void connect(){
        mqtt3Client.connect()
                .whenComplete((conAck, throwable) -> {
                    if (throwable != null) {
                        System.out.println("Connection failed, reason: " + throwable.getMessage());
                    }else{
                        System.out.println("Connected");
                    }
                });
    }

    public void subscriber(String subscriberTopic, Consumer<Mqtt3Publish> subscriberCallback) {
        mqtt3Client.subscribeWith()
                .topicFilter(subscriberTopic)
                .callback(subscriberCallback)
                .send()
                .whenComplete((subAck, throwable) -> {
                    if (throwable != null) {
                        System.out.println("Subscribe failed, reason: " + throwable.getMessage());
                    }else{
                        System.out.println("Subscribed to " + subscriberTopic);
                    }
                });
    }

}
