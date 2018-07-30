package com.ts.messaging.twitter;

import com.ts.messaging.twitter.producer.TweetProducer;

import java.util.Scanner;

public class TweetProducerMain {

    public static void main(String[] args) {
        TweetProducer pr  = new TweetProducer();
        for(int i = 0 ; i < 10 ; ++i) {
            pr.produce();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Press any key to continue");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

    }
}
