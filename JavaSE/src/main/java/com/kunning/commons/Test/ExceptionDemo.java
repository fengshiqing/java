package com.kunning.commons.Test;

public class ExceptionDemo 
{
	public static void main(String[] args) {
		Demo d = new Demo();
		try {
			int x = d.div(4,0);
			System.out.println("x="+x);
		} catch (Exception e) {
			System.out.println("除零啦");
			System.out.println(e.getMessage());//输出：/ by zero
			System.out.println(e);//e.toString()

			e.printStackTrace();// 打印异常名称，异常信息，异常出现的位置
								// JVM 默认的异常处理机制，就是调用printstackTrace方法
								// 打印异常的堆栈跟踪信息
		}
		System.out.print("over");	
	}
}

class Demo {
	int div(int a,int b) {
		return a/b;
	}
}