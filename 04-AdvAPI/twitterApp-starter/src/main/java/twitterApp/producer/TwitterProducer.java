package twitterApp.producer;

import com.twitter.hbc.core.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class TwitterProducer {

    Client twitterClient;
    Logger logger = LoggerFactory.getLogger(TwitterProducer.class.getName());

    public static final String topic = "tweets";

    public static void main(String[] args) {
        new TwitterProducer().run();
    }

    private void run()  {


        //////////Part 1 - Basic Configs ///////////////////
        //Build Tweeter Client

        //Add Shutdown Hook
        addShutDownHook();

        //poll the Queue

        //Print the tweet

        //Create a Kafka Producer

        //Send tweets to Kafka Producer


        //////////Part 2 - Idempotent Producer ///////////////////

        logger.info("Start polling tweets");


    }

    private void addShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
           logger.info("Stopping...");
           twitterClient.stop();
           logger.info("Stpped");
        }));
    }






}
