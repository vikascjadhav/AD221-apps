package com.redhat.training.payments;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PaymentAnalysisRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // TODO: specify JPA endpoint
        from("jpa:com.redhat.training.payments.Payment?consumeDelete=false&persistenceUnit=mysql&maximumResults=5&consumer.delay=3000&consumeLockEntity=false")
            .log("${body}")
            .process(new PaymentFraudAnalyzer())
            .to("sql: update payment_analysis set"+
             " fraud_score=:#${header.fraudScore}, analysis_status='Completed'   where payment_id =:#${body.id}")
            // TODO: add SQL producer endpoint
            .to("direct:payment_analysis_complete?failIfNoConsumers=false&block=false");
    }
}
