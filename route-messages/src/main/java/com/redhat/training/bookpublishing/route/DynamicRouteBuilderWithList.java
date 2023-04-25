package com.redhat.training.bookpublishing.route;

import com.redhat.training.bookpublishing.strategy.DynamicRoutingStrategy1;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DynamicRouteBuilderWithList extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // TODO Auto-generated method stub

        from("file://data/dynamic-input?noop=true")
        .routeId("dynamic-route-with-list")
        .setHeader("destination",method(DynamicRouteBuilderWithList.class,"setHeaderDest"))
        .log("header ${header.destination}")
        .dynamicRouter(method(DynamicRoutingStrategy1.class,"compute"));
        
    }
    
    public String setHeaderDest(Exchange e) {
        String str ="file://data/a,file://data/b,file://data/c";
        return str;
    }

}
