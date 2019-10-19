/*
 * Copyright (C), 2002-2018, XXXXX公司
 * FileName: Algorithm.java
 * Author:   kun
 * DateTime: 2018年5月5日 下午7:17:08
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.kunning.javaSE.algorithm;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author kun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Algorithm {

	/**
	 * 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @param args
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static void main(String[] args) {
		int count = 0;
		for (int i = 101; i < 200; i += 2) {
			boolean isPrimeNum = false;
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					isPrimeNum = false;
					break;
				} else {
					isPrimeNum = true;
				}
			}
			if (isPrimeNum) {
				count++;
				System.out.println(i);
			}
		}
		System.out.println("素数个数是: " + count);
	}

}
