package com.kworkshop.lab03;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class KProducer {

    public static void Produce(String brokers) {

        //task 1.1 create a Kafka Producer
        KafkaProducer<String,String> producer = null;


        // task 1.2 Send 1000 message with Key as the Current time in miliseonds (String) and value - a random string.


        //task 1.3 add a callback an print a confirmation message after the message was sent

    }

    public static void main(String[] args){
        System.out.println("KProducer Main App");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static KafkaProducer<String,String> BuildProducer(String brokers)
    {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers",brokers);
        properties.setProperty("key.serializer",StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName());

        properties.setProperty("acks","1");
        properties.setProperty("retries","3");
        properties.setProperty("linger.ms","1");

        return new KafkaProducer<String,String>(properties);


    }
}
