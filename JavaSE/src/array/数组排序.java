package array;
import java.util.Arrays;
public class 数组排序 {   //类名可以用中文

	public static void main(String[] args) {		
		int[] a = {22, 33, 11, 56, 5};
		System.out.println("排序前：" + Arrays.toString(a));
	
		Arrays.sort(a);  //调用逆序排序方法
		System.out.println("排序后：" + Arrays.toString(a));
	}
	
}

