package com.redhat.training.bookpublishing.route;

import com.redhat.training.bookpublishing.strategy.RoutingSlipStrategy;
import org.apache.camel.builder.RouteBuilder;

public class BookReviewPipelineRouteBuilder extends RouteBuilder {
    private final String ROUTING_HEADER = "destination";

    @Override
    public void configure() throws Exception {

        from("file://data/manuscripts?noop=true")
        .routeId("book-review-pipeline")
        .setHeader(ROUTING_HEADER).method(RoutingSlipStrategy.class)
        .log("File Name ${header.CamelFileName} Destination = ${header.destination}")
        .routingSlip(header("destination"));




    }
}
