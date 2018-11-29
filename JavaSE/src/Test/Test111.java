package Test;

public class Test11 {
    static String sentence = "I don't know.";// 此处不论是静态变量还是非静态变量，都会被局部变量覆盖！
    
    public static void main(String args[]) {
        String abc = "abc";
        if ("abc".equals(abc)) {
            abc = "def";
        } else if ("def".equals(abc)) {// 此时abc = "def";但是if-else语句只会走其中一个分支，这个分支走不到了。
            System.out.println("def");
        }
        
        String sentence = "I know!";// 局部变量会覆盖 静态变量。
        System.out.println(sentence);
        
        System.out.println("-----------------------------------------");
        
        int x = 10;
        System.out.println("Testing Assertion that x==100");
        assert x == 10 : "Out assertion failed!";
        System.out.println("Test passed!");
    }
    
}
