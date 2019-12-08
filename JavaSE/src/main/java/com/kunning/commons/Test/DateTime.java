package com.kunning.commons.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {

	public static void main(String[] args) throws ParseException {
		
		//long startTime = System.currentTimeMillis();//开始时间
		//System.out.println(0.1 + 0.2);
		//long endTime = System.currentTimeMillis();//结束时间
		//System.gc();//运行垃圾回收器。实际上等效于调用：Runtime.getRuntime().gc();
		//System.out.println(endTime - startTime);//打印时间差
		
		//System.out.println(1<<31);//2的31次方，值为负数，因为int型最大可表示2的31次方-1
		//System.out.println(Integer.toBinaryString(1<<31));//2进制表示
		//System.out.println((1<<31)-1);
		//System.out.println(Integer.toBinaryString((1<<31)-1));
		//System.out.println(Byte.MIN_VALUE);
		
		//System.out.println("时间：" + new java.util.Date(0));//显示为8点，因为我们这里是东八区
		//System.out.println("时间：" + new java.util.Date(System.currentTimeMillis()));//等于new Date();

//以下是时间格式的相互转换
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH(hh):mm:ss S E D F w W a k K z");//最全的格式
		SimpleDateFormat sdf24 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
		SimpleDateFormat sdf12 = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");//KK表示12小时制，a表示汉字：上午下午，且位置不固定
		//年份yyyy必须小写，月份MM必须大写，日期dd必须小写；时hh大小写都行，分钟mm必须小写，秒钟ss必须小些,毫秒SSS必须大写
        String curDateTime24 = sdf24.format(new Date()); //当前时间 24小时制
        String curDateTime12 = sdf12.format(new Date()); //当前时间 12小时制
        System.out.println(curDateTime24);
        System.out.println(curDateTime12);
        

        //System.out.println(new Date() + " 或者 " + (new Date()).toLocaleString() + " 或者 " + (new Date()).getTime());//
        //System.out.println(myDate.toString());//等效于 System.out.println(myDate);
        //myDate.setTime(0);
        //System.out.println(myDate);
        
        //Date date = sdf24.parse(curDate);//转换为Date类型
        //System.out.println("时间：" + date);
        
        Calendar past = Calendar.getInstance();
        //past.set(1970, 0, 1, 0, 0, 0);//设置固定时间
        past.set(2016, 4, 14, 0, 0, 0);
        Calendar rightNow = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        rightNow.get(Calendar.YEAR);//获取年
        rightNow.get(Calendar.MONTH);//获取月
        rightNow.get(Calendar.DAY_OF_MONTH);//获取日
        rightNow.get(Calendar.HOUR_OF_DAY);//获取时，24小时制  Calendar.HOUR是12小时制
        rightNow.get(Calendar.MINUTE);//获取分
        rightNow.get(Calendar.SECOND);//获取秒
        rightNow.get(Calendar.HOUR_OF_DAY);//获取秒
        //rightNow.set(Calendar.HOUR_OF_DAY, 0);
        //rightNow.set(Calendar.MINUTE, 0);
        //rightNow.set(Calendar.SECOND, 0);
        //System.out.println("使用默认时区和语言环境获得一个日历:" + rightNow);
/*        System.out.println("使用默认时区和语言环境获得一个日历:" + rightNow.get(Calendar.YEAR)+" "+
													         rightNow.get(Calendar.MONTH)+" "+//0表示1月
													         rightNow.get(Calendar.DAY_OF_MONTH)+" "+
													         rightNow.get(Calendar.HOUR_OF_DAY)+" "+
													         rightNow.get(Calendar.MINUTE)+" "+
													         rightNow.get(Calendar.SECOND));*/
        //long intervalMilli = rightNow.getTimeInMillis() - cal.getTimeInMillis();
        long intervalMillis = System.currentTimeMillis() - past.getTimeInMillis();
        long intervaldate = intervalMillis / (24 * 60 * 60 * 1000);
        System.out.println(intervaldate);
        
        System.out.println(1<<35);
        System.out.println(1<<3);
        
	}
}
