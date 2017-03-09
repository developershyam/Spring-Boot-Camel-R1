package com.sample.jms;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
/**
 * Router is used to read/write file.
 * 
 * @author shyam.pareek
 *
 */
@Component
public class FileCamelRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		String userHome = System.getProperty("user.home");
		from("file:"+userHome+"/camel/input").transacted().process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				Message message = exchange.getIn();
				System.out.println("Sending file: "
						+ message.getHeader("CamelFileName"));
				String fileData = message.getBody(String.class);
				System.out.println(fileData);
				System.out.println("##########  File content received  ##########");
			}
		}).to("file:"+userHome+"/camel/output");

	}
}
