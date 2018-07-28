package com.ts.messaging.twitter.producer;

import com.ts.messaging.twitter.data.Tweet;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import twitter4j.*;


public class TweetProducer extends BaseProducer {

    protected Twitter twitter;

    public TweetProducer() {
        twitter = init().getInstance();
        initProps();
        producer = new KafkaProducer(this.properties);

    }

    public void produce() {
        try {
            produceTweets(TwitterTopics.TWITTERS_TOPIC,Queries.getQueries());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void produceTweets(String topic, List<String> queries) throws Exception{
        for (String query : queries) {
            for (Status status : searchTwitts(query)) {
                produce(new Tweet(createUUID(), status), topic);
            }
        }
    }

    protected List<Status> searchTwitts(String queryString) throws Exception{
        Query query = new Query(queryString);
        query.setCount(100);
        QueryResult result = twitter.search(query);
        return result.getTweets();
    }


    protected TwitterFactory init(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("nGBnBRycdJZNqWsybrOW0fuXA")
                .setOAuthConsumerSecret("UiBUkJrPvDphhHYVG2jcQgSgwed2473bPEldiu37CxlZI7iClI")
                .setOAuthAccessToken("2611487394-XVMbaEiyk6pNHFaHfG7L6K85T4fGL3vtdsAwm5F")
                .setOAuthAccessTokenSecret("0eNzDxmm3zpwWL2UX9ridNq8fHoXSR39CIYS3LYvrKRkT");
        TwitterFactory tf = new TwitterFactory( cb.build() );
        return tf;
    }

    protected Properties initProps(){
        super.initProps();
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.ts.messaging.serde.TweetSerializer");
        return properties;
    }
}
