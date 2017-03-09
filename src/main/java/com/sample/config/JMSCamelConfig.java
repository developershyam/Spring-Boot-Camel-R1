package com.sample.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * This class is designed for JMS configuration.
 * 
 * @author shyam.pareek
 * 
 */
@Configuration
@EnableJms
public class JMSCamelConfig {

	private static final String JMS_BROKER_URL = "vm://embedded?broker.persistent=false,useShutdownHook=false";

	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	@Bean
	public ActiveMQConnectionFactory amqConnectionFactory() {

		ActiveMQConnectionFactory amqConnectionFactory = new ActiveMQConnectionFactory(
				JMS_BROKER_URL);
		amqConnectionFactory.setTrustAllPackages(true);
		return amqConnectionFactory;

	}

	@Bean
	public CachingConnectionFactory connectionFactory() {

		return new CachingConnectionFactory(amqConnectionFactory());

	}

	@Bean
	public JmsTransactionManager createJmsTransactionManager(
			final ConnectionFactory connectionFactory) {

		JmsTransactionManager jmsTransactionManager = new JmsTransactionManager(
				connectionFactory);
		return jmsTransactionManager;
	}

	@Bean
	public JmsComponent createJmsComponent(
			final ConnectionFactory connectionFactory,
			final JmsTransactionManager jmsTransactionManager) {

		JmsComponent jmsComponent = JmsComponent.jmsComponentTransacted(
				connectionFactory, jmsTransactionManager);
		jmsComponent.setMessageConverter(jacksonJmsMessageConverter());
		return jmsComponent;
	}

}
