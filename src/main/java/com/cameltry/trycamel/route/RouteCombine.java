package com.cameltry.trycamel.route;

//import org.apache.camel.AggregationStrategy;
import com.cameltry.trycamel.routeProcess.AggregateCsv;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RouteCombine extends RouteBuilder {

    private static String SEPARATOR = System.getProperty("line.separator");
    @Override
    public void configure() throws Exception {

        from("file://data/orders/outing?noop=true")
                .routeId("routeTwo")
                .split(body().tokenize(SEPARATOR))
                .aggregate(constant(true), new AggregateCsv())
//                    @Override
//                    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
//
//                        if (oldExchange == null) {
//                            return newExchange;
//                        }
//
//                        String newBody = newExchange.getIn().getBody(String.class);
//                        String oldBody = oldExchange.getIn().getBody(String.class);
//
//                        oldExchange.getIn().setBody(newBody + SEPARATOR + oldBody);
//                        return oldExchange;
//                    }
//                })
                .completionSize(10)
                .log("Route TWO ==== File Name : ${header.CamelFileName}")
                .to("file://data/orders/outing?fileName=orders3.csv");
                }
    }

