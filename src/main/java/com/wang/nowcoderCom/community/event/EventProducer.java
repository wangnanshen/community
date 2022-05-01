package com.wang.nowcoderCom.community.event;

import com.alibaba.fastjson.JSONObject;
import com.wang.nowcoderCom.community.entity.Event;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    // 处理事件
    public void fireEvent(Event event) {
        // 消息
        kafkaTemplate.send(event.getTopic(), JSONObject.toJSONString(event));
    }
}
