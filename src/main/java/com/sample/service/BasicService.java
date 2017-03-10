package com.sample.service;

import org.springframework.stereotype.Service;
/**
 * This class is designed like component, utility or any other service reference.
 * 
 * @author shyam.pareek
 *
 */
@Service("basicService")
public class BasicService {

	public int sumAll(int ...a){	
		
		System.out.println("BasicService#sumAll executed ");
		int sum = 0;
		for (int i : a) {
			sum+=i;
		}
		return sum;
	}
}
