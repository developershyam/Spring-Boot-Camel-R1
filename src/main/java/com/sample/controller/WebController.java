package com.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

	String INFO = "{ JMS Topic BaseURL ==> /publishJMSMessage }";

	@RequestMapping("/")
	public String home() {
		return INFO;
	}

	@RequestMapping("/publishJMSMessage")
	public String publishJMSMessage() {

		jmsService.publishJMSMessage();
		return "Your message is published. Please check console/log!!! " + INFO;
	}

	

}
