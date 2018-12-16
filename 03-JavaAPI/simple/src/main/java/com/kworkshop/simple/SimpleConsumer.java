package com.kworkshop.simple;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

public class SimpleConsumer {


    private static KafkaConsumer<String,String> consumer ;
    private static String topic = "my-topic";

    public static void main(String[] args) {

        String brokers = "localhost:9092";
        String groupId;
        if(args.length > 0) {
            groupId = args[0];
        } else {
            groupId = UUID.randomUUID().toString();
        }

        Consume(brokers, groupId);
    }

    protected static void Consume(String brokers, String groupId) {


        consumer = BuildConsumer(brokers,groupId);
        consumer.subscribe(Arrays.asList(topic));

        int count = 0;
        System.out.println("Start consuming from topic " +topic);
        while(true) {

            ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(500));
            for(ConsumerRecord<String,String> record : records) {
                // Display record and count
                count += 1;
                System.out.println( count + ": " + record.value());
            }

        }

    }

    public static KafkaConsumer<String,String> BuildConsumer(String brokers, String groupId)
    {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers",brokers);
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());

        properties.setProperty("enable.auto.commit","true");
        properties.setProperty("group.id",groupId);
        // When a group is first created, it has no offset stored to start reading from. This tells it to start
        // with the earliest record in the stream.
        properties.setProperty("auto.offset.reset","earliest");

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);
        return consumer;
    }


}
