package com.sample.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Router is used to call bean/class ref methods.
 * 
 * @author shyam.pareek
 *
 */
@Component
public class BeanRouteSum extends RouteBuilder  {
	@Override
	public void configure() throws Exception {
		from("jms:bean.sum").bean("basicService", "sumAll").transacted().process(new Processor() {

			@Override
			public void process(Exchange ex) throws Exception {
				
				System.out.println("Processor >>> " + ex);
				int message = ex.getIn().getBody(Integer.class);
				System.out.println("########## Sum: "+message+" ##########");
			}
		});		
		
	}
}
