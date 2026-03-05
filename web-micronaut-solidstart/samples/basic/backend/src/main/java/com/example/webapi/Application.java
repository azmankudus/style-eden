package com.example.webapi;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "basic", version = "1.0.0"))
public class Application {

    public static void main(final String[] args) {
        Micronaut.run(Application.class, args);
    }
}
