package com.rong.example.kafka.producer;

import com.alibaba.fastjson.JSON;
import com.rong.example.kafka.KafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class Producer {

    @Autowired
    private KafkaTemplate kafkaTemplate;


    /**
     * 简单用法
     */
    public void send() {
        KafkaMessage message = new KafkaMessage();
        message.setId("kafka_"+System.currentTimeMillis());
        message.setMsg("来消息了");
        message.setSendTime(new Date());

        //方法里面还是封装的 ProducerRecord
        kafkaTemplate.send("example_first_kafka", JSON.toJSONString(message));
        log.info("发送kafka消息："+JSON.toJSONString(message));
    }

    /**
     * 添加消息头
     */
    public void sendWithHead(){

        KafkaMessage message = new KafkaMessage();
        message.setId("kafka_"+System.currentTimeMillis());
        message.setMsg("带消息头的来了");
        message.setSendTime(new Date());

        List<Header> headers = new ArrayList<>();
        headers.add(new RecordHeader("x-example-event", "login".getBytes()));

        ProducerRecord<String, String> bar = new ProducerRecord("example_first_kafka",
                null, "111", JSON.toJSONString(message), headers);

        kafkaTemplate.send(bar);
        log.info("发送带消息头的kafka消息："+JSON.toJSONString(message));
    }


}
