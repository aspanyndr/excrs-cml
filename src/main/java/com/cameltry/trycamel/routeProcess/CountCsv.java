package com.cameltry.trycamel.routeProcess;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountCsv implements Processor {
    private static String separator = System.getProperty("line.separator");

    @Override
    public void process(Exchange exchange) throws Exception {
        String inputMessage = exchange.getIn().getBody( String.class );

        AtomicReference<Long> counter = new AtomicReference<>(1L);

        String processedLines = Stream.of(inputMessage.split(separator))
                .map( l -> counter.getAndUpdate( p -> p + 1 ).toString() + "," + l )
                .collect(Collectors.joining(separator));

        exchange.getIn().setBody( processedLines );
    }

}
