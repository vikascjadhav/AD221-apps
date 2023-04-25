package com.redhat.training.payments.transaction;

import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class PaymentsTransactionPolicy {
    // TODO: Create a transaction management policy

    

    @Bean("txnPolicy")
    public SpringTransactionPolicy getPolicy(PlatformTransactionManager mgr) {
        SpringTransactionPolicy policy = new SpringTransactionPolicy();
        policy.setPropagationBehaviorName("PROPAGATION_REQUIRED");
        policy.setTransactionManager(mgr);
        return policy;
    }


}
