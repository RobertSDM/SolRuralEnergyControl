package com.mai.solar.energyControl.services;

import com.hivemq.client.mqtt.mqtt3.message.publish.Mqtt3Publish;
import com.mai.solar.energyControl.managers.HiveMqConnectionManager;
import com.mai.solar.energyControl.models.interfaces.HiveMQSubscriber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class HiveMQSubscriberSolarDataService implements HiveMQSubscriber {

    private final HiveMqConnectionManager hiveMqConnectionManager;

    @Value("${hivemq.subscriber.solar-data.topic}")
    private String subscriberTopic;


    public HiveMQSubscriberSolarDataService(HiveMqConnectionManager hiveMqConnectionManager) {
        this.hiveMqConnectionManager = hiveMqConnectionManager;
    }

    @PostConstruct
    public void connect(){
        hiveMqConnectionManager.subscriber(subscriberTopic, this::messageHandler);
    }

    public void messageHandler(Mqtt3Publish publish){
        if(publish.getPayload().isPresent()){

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.now();

            System.out.println();
            System.out.println(dateFormatter.format(dateTime) + ": " + "Data received on topic: " + publish.getTopic());
            ByteBuffer payload = publish.getPayload().get();
            String data = StandardCharsets.UTF_8.decode(payload).toString();
        }
    }

}
