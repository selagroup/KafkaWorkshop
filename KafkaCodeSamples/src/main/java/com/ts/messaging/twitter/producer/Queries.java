package com.ts.messaging.twitter.producer;

import java.util.ArrayList;
import java.util.List;

public final class Queries {

    private static final List<String> queries = new ArrayList<>();

    static {

        queries.add("q=worldCup");
        queries.add("q=worldcup2018");
        queries.add("q=aws");
        queries.add("q=confluent");
        queries.add("q=TrumpKimSummit");
    }


    private Queries() {
    }

    public static List<String> getQueries() {
        return queries;
    }
}