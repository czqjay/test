package com.sunit.global.util;

import java.util.Random;

/**
 * 
* @ClassName: RandomGenerator 
* @Description: TODO(生成随机数类) 
* @author liangrujian 
* @date Jul 1, 2016 6:47:54 PM
 */
public class RandomGeneratorUtil {
	
	
	/**
	 * @Title: randomGenerator
	 * @Description: (生成随机码)
	 * @param
	 * @param num
	 * @param
	 * @return
	 * @return String
	 * @author：liangrujian
	 * @date : Oct 29, 2015 12:45:51 PM
	 */
	public static String randomGenerator(int num) {
		String tempResult = "";
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			tempResult = tempResult + random.nextInt(10);
			;
		}

		return tempResult;
	}
}
