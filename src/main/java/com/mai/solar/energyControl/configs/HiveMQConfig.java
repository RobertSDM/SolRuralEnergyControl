package com.mai.solar.energyControl.configs;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class HiveMQConfig {

    @Bean
    public Mqtt3AsyncClient hivemqClient() {
        return MqttClient.builder()
                .useMqttVersion3()
                .identifier(UUID.randomUUID().toString())
                .serverHost("broker.hivemq.com")
                .serverPort(1883)
                .buildAsync();
    }

}
