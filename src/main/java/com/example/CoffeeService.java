package com.example;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.example.client.CoffeeResource;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class CoffeeService {

    @Inject
    @RestClient
    CoffeeResource coffeeResource;

    public Collection<Coffee> getCoffees() {
        System.out.println("Kogito calling our coffee-service microservice!");
        return coffeeResource.getCoffees();
    }

}
