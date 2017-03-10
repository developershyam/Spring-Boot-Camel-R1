package com.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.service.JMSService;

/**
 * This is Rest controller used to expose URL for application access.
 * 
 * @author shyam.pareek
 *
 */
@RestController
public class WebController {

	@Autowired
	private JMSService jmsService;

	String INFO = "{ JMSPublishMessageURL ==> /publishJMSMessage, PublishMessageBeanRefURL ==> /publishMessageSum?input=1,2,3}";

	@RequestMapping("/")
	public String home() {
		return INFO;
	}

	@RequestMapping("/publishJMSMessage")
	public String publishJMSMessage() {

		jmsService.publishJMSMessage();
		return "Your message is published. Please check console/log!!! " + INFO;
	}

	@RequestMapping("/publishMessageSum")
	public String publishMessageSum(@RequestParam String input) {

		String a[] = input.split(",");
		int val[] = new int[a.length];
		for (int i=0 ; i<val.length; i++) {
			val[i] =Integer.valueOf(a[i]);
		}
		jmsService.publishMessageSum(val);
		return "Your message is published. Please check console/log!!! " + INFO;
	}
	

}
