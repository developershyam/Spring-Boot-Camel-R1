package com.sample.service;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.jms.JMSMessage;

/**
 * Business layer for JMS services. Publish message.
 * 
 * @author shyam.pareek
 * 
 */
@Service
public class JMSServiceImpl implements JMSService {

	@Autowired
	CamelContext camelContext;
	
	@Autowired
	ProducerTemplate producerTemplate;

	/**
	 * Publish JMS message.
	 */
	@Override
	public void publishJMSMessage() {

		JMSMessage message = new JMSMessage(
				"JMS message with Apache Camel !!!", true, false);

		try {

			producerTemplate.sendBody("jms:myQueue", message);
			System.out.println("********** Your message is published  "
					+ message + "  **********");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
