package com.example;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class CoffeeRouteBuilder extends RouteBuilder {

    @ConfigProperty(name = "coffee.resource.camel.url", defaultValue = "netty-http:http://localhost:8090/coffee")
    String url;

    @Override
    public void configure() throws Exception {

        JacksonDataFormat format = new ListJacksonDataFormat(Coffee.class);

        from("direct://getCoffees").log("Get Coffee Route Triggered: ${body}")
        .setHeader("Accept").constant(MediaType.APPLICATION_JSON)
        .setHeader("CamelHttpMethod").constant("GET")
        .to(url)
        .unmarshal(format);

    }

}
