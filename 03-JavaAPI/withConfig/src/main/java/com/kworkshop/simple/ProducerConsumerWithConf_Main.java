package com.kworkshop.simple;

import java.io.IOException;

public class ProducerConsumerWithConf_Main {

    public static void main(String[] args) throws IOException {
        if(args.length < 1) {
            usage();
        }
        switch(args[0].toLowerCase()) {
            case "producer":
                SimpleProducer.Produce();
                break;
            case "consumer":
                SimpleConsumer.Consume();
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
