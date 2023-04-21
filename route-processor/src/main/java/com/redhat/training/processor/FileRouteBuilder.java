package com.redhat.training.processor;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileRouteBuilder extends RouteBuilder {

    private static String separator = System.getProperty("line.separator");
    

    @Override
    public void configure() throws Exception {
        from( "file:orders/incoming?noop=true" )
                // .process()
                .process( new Processor() {

                    @Override
                    public void process(Exchange exchange) throws Exception {
                        AtomicReference<Long> counter = new AtomicReference<>(1L);
                        String inpuString = exchange.getIn().getBody(String.class);
                        String processSedLines =  Stream.of(inpuString.split(separator))
                         .map(line -> {  
                            if(line ==null) {
                                return "";
                            }
                            System.out.println("line = "+line);
                            return  counter.getAndUpdate(c -> c+1).toString() + ","+ line; 
                        
                        
                         }).collect(Collectors.joining(separator));
                        
                        
                         exchange.getIn().setBody(processSedLines);
                        
                    }
                    
                })
                .to( "file:orders/outgoing?fileName=orders2.csv" );
    }
}
