package com.example;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.camel.CamelContext;
import org.apache.camel.FluentProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class CoffeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoffeeService.class);

    @Inject
    CamelContext camelContext;

    private FluentProducerTemplate producer;

    @PostConstruct
    void init() {
        producer = camelContext.createFluentProducerTemplate();
        producer.setDefaultEndpointUri("direct://getCoffees");
    }

    @PreDestroy
    void destroy() {
        producer.stop();
    }

    @SuppressWarnings("unchecked")
    public Collection<Coffee> getCoffees() {
        LOGGER.debug("Retrieving coffees");
        return producer.request(Collection.class);
    }

}
