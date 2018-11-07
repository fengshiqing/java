package com.itheima.exception;

public class MsgException extends Exception {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	public MsgException() {
	}

	public MsgException(String msg) {
		super(msg);
	}

}
