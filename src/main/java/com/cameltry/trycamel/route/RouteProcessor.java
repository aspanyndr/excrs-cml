package com.cameltry.trycamel.route;

import com.cameltry.trycamel.routeProcess.CountCsv;
//import org.apache.camel.Exchange;
//import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RouteProcessor extends RouteBuilder {
//    private static String SEPARATOR = System.getProperty("line.separator");

    @Override
    public void configure() throws Exception {
        from("file://data/orders/incoming?noop=true")
                .routeId("RouteOne")
                .process(new CountCsv())
                .log("Route ONE ==== File Name : ${header.CamelFileName}")
                .to("file://data/orders/outing?fileName=orders2.csv");

    }
}
