package array;

public class javautilsArray {

	public static void main(String[] args) {
		int[] number = {9,3,1,5,7};
		java.util.Arrays.sort(number);// java API中自带的排序函数.
		for(int i :number) 
			System.out.print(i + " ");
	}

}
