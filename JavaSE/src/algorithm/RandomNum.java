package algorithm;

import java.util.Random;

/**
 * @author kun
 * 随机数
 */
public class RandomNum {

	public static void main(String[] args) {
		// 产生一个[0，1)之间的随机数,大于等于 0.0 且小于 1.0 的[0,1)范围内的 double 类型的数
		System.out.println("[0,1)范围内的随机数：" + Math.random());//Random类也可以生成随机数，具体的看API文档
		System.out.println("[1,10)范围内的随机数：" + 1 + Math.random()*9);//从1到10的int型随数
		System.out.println("获取范围内随机数：" + suijishu(1,10));
		//suijiString();
		//System.out.println(randNum(1,10));
	}

	// 获取 从 start 到 end 之间的随机整数
	public static int suijishu(int start, int end) {
		int num = (int) (Math.random()*(end-start+1)+start);
		return num;
	}
	
 	public static String suijiString() {
		String result="";
		for(int i=0;  i<6;  i++) {//循环6次，生成6位字符串，可以提取位数作为参数，又用户确定生成几位字符串
			int intValue=(int)(Math.random()*26+97);//生成[97,123)的double数，强转成[97-122]的int型的整型
			result = result+(char)intValue;//将intValue强制转化成char类型后接到result后面
		}
		return result;
	}
	
	// 生成 begin 到 end 之间的随机数
	public static int randNum(int begin, int end) {
		Random rand = new Random();
		return rand.nextInt(end-begin) + begin;
	}
	
}
