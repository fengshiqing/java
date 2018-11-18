/**
 * 
 */
package log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author kun
 *
 */
public class LoggerDemo {


	/**
	 * <日志>
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerDemo.class);

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOGGER.info("【么么哒】【开始执行】");

	}

}
