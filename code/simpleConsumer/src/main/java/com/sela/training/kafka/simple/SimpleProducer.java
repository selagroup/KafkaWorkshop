package com.sela.training.kafka.simple;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class SimpleProducer {


    public static void Produce(String brokers) throws IOException {

        KafkaProducer<String,String> producer  = BuildProducer(brokers);

        // So we can generate random sentences
        Random random = new Random();
        String[] sentences = new String[] {
                "the cow jumped over the moon",
                "an apple a day keeps the doctor away",
                "four score and seven years ago",
                "snow white and the seven dwarfs",
                "i am at two with nature"
        };

        String progressAnimation = "|/-\\";
        // Produce a bunch of records
        for(int i = 0; i < 1000; i++) {
            // Pick a sentence at random
            String sentence = sentences[random.nextInt(sentences.length)];
            // Send the sentence to the test topic
            producer.send(new ProducerRecord<String, String>("my-topic", sentence));
            String progressBar = "\r" + progressAnimation.charAt(i % progressAnimation.length()) + " " + i;
            System.out.write(progressBar.getBytes());
        }

        System.in.read();

    }



    public static KafkaProducer<String,String> BuildProducer(String brokers)
    {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers",brokers);
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        properties.setProperty("acks","1");
        properties.setProperty("retries","3");
        properties.setProperty("linger.ms","1");

        return new KafkaProducer<String,String>(properties);


    }
}



