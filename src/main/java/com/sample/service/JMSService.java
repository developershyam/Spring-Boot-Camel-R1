package com.sample.service;
/**
 * Abstract layer for JMS services. 
 * 
 * @author shyam.pareek
 *
 */
public interface JMSService {

	void publishJMSMessage();
	void publishMessageSum(int val[]) ;
	
	
}
