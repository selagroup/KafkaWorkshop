package com.sela.training.kafka.simple;

import java.io.IOException;
import java.util.UUID;

public class ProducerConsumerMain {

    public static void main(String[] args) throws IOException {
        if(args.length < 1) {
            usage();
        }

        // Get the brokers
        String brokers = "localhost:9092";
        switch(args[0].toLowerCase()) {
            case "producer":
                SimpleProducer.Produce(brokers);
                break;
            case "consumer":
                // Either a groupId was passed in, or we need a random one
                String groupId;
                if(args.length == 2) {
                    groupId = args[1];
                } else {
                    groupId = UUID.randomUUID().toString();
                }
                SimpleConsumer.Consume(brokers, groupId);
                break;
            default:
                usage();
        }
        System.exit(0);
    }
    // Display usage
    public static void usage() {
        System.out.println("Usage:");
        System.out.println("kafka-example.jar <producer|consumer> [groupid]");
        System.exit(1);
    }
}
