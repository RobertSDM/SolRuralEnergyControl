package com.mai.solar.energyControl.services;

import com.google.gson.Gson;
import com.hivemq.client.mqtt.mqtt3.message.publish.Mqtt3Publish;
import com.mai.solar.energyControl.managers.HiveMqConnectionManager;
import com.mai.solar.energyControl.models.EnergyHistory;
import com.mai.solar.energyControl.models.dto.EnergyHistoryHiveMQDTO;
import com.mai.solar.energyControl.models.interfaces.HiveMQSubscriber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class HiveMQSubscriberSolarDataService implements HiveMQSubscriber {

    private final HiveMqConnectionManager hiveMqConnectionManager;
    private final Gson gson;
    private final EnergyHistoryService energyHistoryService;


    @Value("${hivemq.subscriber.solar-data.topic}")
    private String subscriberTopic;

    public HiveMQSubscriberSolarDataService(HiveMqConnectionManager hiveMqConnectionManager, Gson gson, EnergyHistoryService energyHistoryService) {
        this.hiveMqConnectionManager = hiveMqConnectionManager;
        this.gson = gson;
        this.energyHistoryService = energyHistoryService;
    }

    @EventListener(ContextRefreshedEvent.class)
    @Order(2)
    public void connect() {
        hiveMqConnectionManager.subscriber(subscriberTopic, this::messageHandler);
    }

    public void messageHandler(Mqtt3Publish publish) {
        if (publish.getPayload().isEmpty()) {
            return;
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.now();

        System.out.println();
        System.out.println(dateFormatter.format(dateTime) + ": " + "Data received on topic: " + publish.getTopic());
        ByteBuffer payload = publish.getPayload().get();
        String dataString = StandardCharsets.UTF_8.decode(payload).toString();

        try {
            EnergyHistoryHiveMQDTO data = gson.fromJson(dataString, EnergyHistoryHiveMQDTO.class);
            EnergyHistory energyHistory = energyHistoryService.create(data.panelId(), data.farmId(), data.energyValue());

            energyHistoryService.save(energyHistory);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
