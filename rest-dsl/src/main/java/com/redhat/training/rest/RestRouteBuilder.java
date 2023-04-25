package com.redhat.training.rest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
@Component
public class RestRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // TODO: configure rest-dsl
        // to use servlet component and run on port 8080
            restConfiguration("servlet")
            .port(8080)
            .bindingMode(RestBindingMode.json);

            rest("payments")
            .get("/")
                .to("direct:allPayments")
            .get("/{userId}")
                .to("direct:getPayments");


            from("direct:allPayments")
            .routeId("allPayments")
                .to("jpa:com.redhat.training.rest.Payment?query=select p from com.redhat.training.rest.Payment p");

            from("direct:getPayments")
            .routeId("getPayments")
            .log("get payment for user ${header.userId}")
                .toD("jpa:com.redhat.training.rest.Payment?"+
                "query=select p from com.redhat.training.rest.Payment p where userId = ${header.userId}");

        // TODO: rest services under the payments context-path
		
		// TODO: routes that implement the REST services
    }
}
