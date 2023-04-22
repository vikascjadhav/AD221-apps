package com.redhat.training.combine;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CombineRouteBuilder extends RouteBuilder {
    private static String SEPARATOR = System.getProperty("line.separator");

    @Override
    public void configure() throws Exception {
        from( "file:orders/incoming?noop=true" )
	.routeId( "split-combine-pipeline" )
    .split(body().tokenize(SEPARATOR))
    .aggregate(constant(true), new AggregationStrategy() {

        @Override
        public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
             if(oldExchange == null) {
                 return newExchange;
             }

             String oldMsg = oldExchange.getIn().getBody(String.class);
             String newMsg = newExchange.getIn().getBody(String.class);

             newExchange.getIn().setBody(oldMsg+SEPARATOR+newMsg);
            return newExchange;
        }
        
    }).completionSize(10)
        .to( "file:orders/outgoing?fileName=orders2.csv" );
    }
}
