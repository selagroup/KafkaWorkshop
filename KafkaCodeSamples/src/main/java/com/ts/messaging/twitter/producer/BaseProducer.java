package com.ts.messaging.twitter.producer;

import com.ts.messaging.twitter.data.Identity;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.UUID;

public class BaseProducer {

    protected Producer producer;
    protected Properties properties = null;


    protected <K, T extends Identity> void produce(T record, String topic) {
        producer.send(new ProducerRecord(topic, record.getKey(), record));
    }

    protected String createUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }



    protected Properties initProps() {
        properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("acks", "all");
        properties.put("retries", 10);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }

    protected void close() {
        System.out.println("---------producer close method is called!!----------");
        producer.close();
    }
}

