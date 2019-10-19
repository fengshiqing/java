package com.kunning.JavaSE.枚举;

public class TestSeason1 {

	public static void main(String[] args) {
		Season spring = Season.SPRING;
		System.out.println(spring);
		spring.show();
		System.out.println(spring.getSeasonName());
		System.out.println(spring.getSeasonDesc());
		
		// 常用的枚举类的方法
		Season1[] seasons = Season1.values();
		for(int i=0; i<seasons.length; i++) {
			System.out.println(seasons[i]);
		}
		
		Season1 season = Season1.valueOf("SPRING");// "SPRING"是枚举类的名字
		System.out.println(season);
		season.show();
	}

}

interface info {
	void show();
}

// 用关键字“enum”定义季节枚举类
enum Season1 implements info{
	
	// 必须将枚举类的对象放在最上面，然后精简代码，把一样的都去掉
	// 可以连属性都没有，如Thread类的state内部类
	SPRING("spring", "穿暖花开"){
		@Override
		public void show() {
			System.out.println("春");
		}
	},
	SUMMER("summer", "夏日炎炎"){
		@Override
		public void show() {
			System.out.println("夏天");
		}
	},
	AUTUMN("autumn", "秋高气爽"){
		@Override
		public void show() {
			System.out.println("秋");
		}
	},
	WINTER("winter", "白雪皑皑"){
		@Override
		public void show() {
			System.out.println("冬");
		}
	};

	private final String seasonName;
	private final String seasonDesc;

	// 2、私有化构造器。final类型的属性一旦初始化就不可修改。
	private Season1(String seasonName, String seasonDesc) {
		this.seasonName = seasonName;
		this.seasonDesc = seasonDesc;
	}

	// 3、通过公共方法调用属性
	public String getSeasonName() {
		return seasonName;
	}

	public String getSeasonDesc() {
		return seasonDesc;
	}

	@Override
	public String toString() {
		return "Season [seasonName=" + seasonName + ", seasonDesc=" + seasonDesc + "]";
	}

	@Override
	public void show() {
		System.out.println("这是一个季节");
	}

}
