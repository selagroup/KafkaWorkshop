package com.kw.lab03;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class KConsumer {

    protected static void Consume(String brokers, String groupId) {

        //task 1.4 create a consumer with a random group ID.
        KafkaConsumer<String,String> consumer = null;
        //task 1.5 register for the topic my-topic



        while(true) {
            //task 1.5 : poll messages from the topic and print the following information:
            //parition : 1 , Offset : 0 , Key : my-key , Value : my-value


            //sleep 1 milisecond between messages
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }




        }

    }

    public static void main(String[] args){
        System.out.println("KConsumer Main App");

        try {

            //task 1.6 : run 1 consuer and 1 producer to verify the results
            //change the producer to producer infinite stream at a rate of 100 messages per second.

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
