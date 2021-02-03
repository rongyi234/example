package com.rong.example.kafka.consumer;

import com.alibaba.fastjson.JSON;
import com.rong.example.kafka.KafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 消费者
 */
@Component
@Slf4j
public class Consumer {

    /**
     * @Header("x-example-event") String event   -->  直接获取消息头，但是为空的时候报错
     *
     */
    @KafkaListener(topics = {"example_first_kafka"})
    public void listen(ConsumerRecord<?, ?> record, @Header("x-example-event") String event){

        log.info("consumerRecord= "+record);

        try {
            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
            if (kafkaMessage.isPresent()) {

                Object message = kafkaMessage.get();

                String resultEvent = event==null ? "null":event;
                log.info("消费kafka消息：header：event="+resultEvent+"; message="+ message);

                KafkaMessage kafkaMsg=JSON.parseObject((String) message,KafkaMessage.class);

            }
        } catch (Exception e) {
            log.error("kafka消费异常了",e);
        }

    }
}
