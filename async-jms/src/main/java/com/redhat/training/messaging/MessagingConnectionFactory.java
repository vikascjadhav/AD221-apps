package com.redhat.training.messaging;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import org.apache.camel.component.jms.JmsComponent;

import javax.jms.JMSException;


@Configuration
public class MessagingConnectionFactory {

	@Bean
	public JmsComponent getJmsComponent() throws JMSException {

		ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();

		cf.setBrokerURL("tcp://localhost:61616");
		cf.setUser("admin");
		cf.setPassword("admin");

		JmsComponent jms = new JmsComponent();

		jms.setConnectionFactory(cf);
		return jms;
	}
	
}
