package log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 学习日志框架
 * 
 * @author 冯仕清
 *
 */
public class LoggerDemo {

	/**
	 * 日志
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerDemo.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String name = Logger.ROOT_LOGGER_NAME;  
		System.out.println(name); //false  说明root无法通过name获取  
		
		System.out.println(LOGGER.getName());
		System.out.println(LOGGER.isDebugEnabled());
		System.out.println(LOGGER.isInfoEnabled());
		
		LOGGER.info("【么么哒】【开始执行】【亲爱的{}】", "李双");
	}

}
