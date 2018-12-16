package com.kworkshop.lab03;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

public class KConsumer {

    protected static void Consume(String brokers, String groupId) {

        //task 1.4 create a consumer.
        KafkaConsumer<String,String> consumer = BuildConsumer(brokers,groupId);
        //task 1.5 register for the topic my-topic
        consumer.subscribe(Arrays.asList("my-topic"));


        while(true) {
            //task 1.5 : poll messages from the topic and print the following information:
            //parition : 1 , Offset : 0 , Key : my-key , Value : my-value
            ConsumerRecords<String,String> records = consumer.poll(500);
            for(ConsumerRecord<String,String>  rec : records) {
                System.out.println("Partition :" + rec.partition() + " , Offset : " + rec.offset() + " , Key :" + rec.key() + " ,Value :" + rec.value());
            }
            //sleep 1 milisecond between messages
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //task 1.6 : run 1 consuer and 1 producer to verify the results
        }

    }

    public static void main(String[] args){
        System.out.println("KConsumer Main App");
        Consume("127.0.0.1:9092", UUID.randomUUID().toString());

        //task 1.6 : run 1 consuer and 1 producer to verify the results
        //change the producer to producer infinite stream at a rate of 100 messages per second.

        //Consume("127.0.0.1:9092", "grp1");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static KafkaConsumer<String,String> BuildConsumer(String brokers,String groupId)
    {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers",brokers);
        properties.setProperty("key.deserializer",StringDeserializer.class.getName());
        properties.setProperty("value.deserializer",StringDeserializer.class.getName());
        properties.setProperty("enable.auto.commit","true");
        properties.setProperty("auto.offset.reset","earliest");
        properties.setProperty("group.id",groupId);

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);
        return consumer;
    }


}
