package basics;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int A, B, C;
        Scanner input = new Scanner(System.in);// 控制台输入
        System.out.print("请输入");
        A = input.nextInt();
        B = input.nextInt();
        C = input.nextInt();
        System.out.println("您输入的是" + input);
        input.close();
        System.out.println(A + B + C);
        
    }

}