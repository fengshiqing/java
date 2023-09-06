/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.bank.face;

import org.apache.commons.httpclient.methods.PostMethod;

public class GBKPostMethod extends PostMethod {
	public GBKPostMethod(String url) {
		super(url);
	}
	public String getRequestCharSet() { 
		return "GBK"; 
	} 

}
