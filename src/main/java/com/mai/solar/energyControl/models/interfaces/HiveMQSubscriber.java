package com.mai.solar.energyControl.models.interfaces;

import com.hivemq.client.mqtt.mqtt3.message.publish.Mqtt3Publish;

public interface HiveMQSubscriber {

    void messageHandler(Mqtt3Publish publish);

    void connect();

}
