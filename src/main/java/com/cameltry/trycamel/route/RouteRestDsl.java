package com.cameltry.trycamel.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class RouteRestDsl extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        restConfiguration()
                .port(8080)
                .host("localhost")
                .component("servlet")
                .bindingMode(RestBindingMode.json);

        rest()
                .get("/products")
                .to("direct:getProducts");

        from("direct:getProducts")
                .to("https://dummyjson.com/products")
                .log("${body}");

    }
}
