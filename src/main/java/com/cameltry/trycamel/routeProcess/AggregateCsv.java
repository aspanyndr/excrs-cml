package com.cameltry.trycamel.routeProcess;

//import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class AggregateCsv implements AggregationStrategy {
    private static final String SEPARATOR = System.getProperty("line.separator");

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        if(oldExchange ==null){
            return newExchange;
        }

        String newBody = newExchange.getIn().getBody(String.class);
        String oldBody = oldExchange.getIn().getBody(String.class);

        oldExchange.getIn().setBody(newBody + SEPARATOR + oldBody);
        return oldExchange;
    }
}
