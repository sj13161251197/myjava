package java8_lambda.default_static;

class D2 implements A,E{

	@Override
	public void test() {
        System.out.println("D2");		
	}
	public static void main(String[] args) {
		   D2 d2=new D2();
		   d2.printA();//System.out.println("A"+i);,System.out.println("接口间默认方法的继承");
		   
		  /* 
		   * 
		   * E:
		  @Override
			default void printA(){
				//printC()执行时会调用E中的printA方法，形成死循环
				 //  printC();
				   A.super.printA();
				   System.out.println("接口间默认方法的继承");
				}*/
		   
		   //A
		   /*default void printA(){
				System.out.println("A"+i);
			}
		   default void printC(){
			   System.out.println("C");
			   //默认方法中可以访问静态方法或者默认方法
			   printB();
			   printA();
		   }
		   static void printB(){
				//static方法中不能访问default方法，只能访问静态方法
				//printC()
				System.out.println("静态方法A");
			}
			static void printD(){
			    printB();
			}
		   */
		   d2.printC();//System.out.println("C");System.out.println("静态方法A");System.out.println("A"+i);
		}
	
}