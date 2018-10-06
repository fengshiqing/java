package array;
import java.util.Arrays;
public class 数组排序 {   //类名可以用中文

	public static void main(String[] args) {		
		int[] a = {22, 33, 11, 56, 5};
		System.out.println("排序前的数组：");
		paiXu(a);
	
		Arrays.sort(a);  //调用逆序排序方法
		System.out.println("排序后的数组：");
		paiXu(a);
	}
	
	private static void paiXu(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println("a[" + i + "]=" + a[i] + " ");
		}
	}
}

