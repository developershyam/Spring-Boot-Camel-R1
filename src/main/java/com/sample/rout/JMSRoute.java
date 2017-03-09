package com.sample.rout;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.sample.jms.JMSMessage;
/**
 * Router is used to read/write message from JMS.
 * 
 * @author shyam.pareek
 *
 */
@Component
public class JMSRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("jms:myQueue").transacted().process(new Processor() {

			@Override
			public void process(Exchange ex) throws Exception {
				
				System.out.println(">>>>>>>>>>> " + ex);
				JMSMessage message = ex.getIn().getBody(JMSMessage.class);
				message.setJmsReceivedStatus(true);
				System.out.println("########## Received: "+message+" ##########");
			}
		});		
		
	}
}
