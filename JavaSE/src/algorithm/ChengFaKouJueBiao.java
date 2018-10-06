package algorithm;

/**
 * @author kun
 * 打印乘法口诀表
 */
public class ChengFaKouJueBiao {

	public static void main(String[] args) {
		chengFaKouJueBiao();
		System.out.println();//加一行空格
		mulTable();
	}
	
	private static void chengFaKouJueBiao() {
		long startTime = System.currentTimeMillis();//开始时间
		for(int i=1,j=1; j<=9; i++) {
			System.out.print(i + "x" + j + "=" + i*j + " ");
			if(i == j) {
				System.out.println();
				i=0;//下一行将i 重置为0，因为之后会进行 ++ 运算
				j++;
			}
		}
		long endTime = System.currentTimeMillis();//结束时间
		long time = endTime-startTime;
        System.out.println("用时：" + time);
	}
	
	protected static void mulTable() {
        for(int i=1;  i<=9;  i++) {
            for(int j=1;  j<=i;  j++) {
                System.out.print(i + "*" + j + " = " + i*j + "   ");
            }
            System.out.print("\n");
        }
	}
	
	
}
