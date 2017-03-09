package com.sample.service;

import org.springframework.stereotype.Service;

@Service("beanSumService")
public class BeanSumService {

	public int sum(int ...a){	
		int sum = 0;
		for (int i : a) {
			sum+=i;
		}
		System.out.println(">>>>>"+sum);
		return sum;
	}
}
