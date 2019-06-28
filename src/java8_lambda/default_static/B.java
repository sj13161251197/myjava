package java8_lambda.default_static;

interface B{
	void test();
	default void printA(){
		System.out.println("同名方法B");
	}
	static void printB(){
		System.out.println("静态方法B");
	}
}
