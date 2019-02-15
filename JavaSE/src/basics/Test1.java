package basics;

public class Test1 {

	public static void m() {
		System.out.println("m()");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test1 t = null;
		t.m();// m()方法是static的，应该用类名.方法名调用，这样调用也行。
	}

}
