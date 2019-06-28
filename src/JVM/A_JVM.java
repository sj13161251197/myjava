package JVM;

/**
 * java变量定义 jvm规范：主要定义二进制class文件和JVM指令集等 test2打印二进制 test3进制的转换
 * 
 * @author Administrator
 *
 */
public class A_JVM {
	private int a = 0xDada_Cafe;
	private float b = 0x1.fffffeP+127f;
	private float c = 1996;
	// private float d=1996.3;
	private float d = 1996.3f;
	// private int f=9999e2;
	private int f = 0x99992;
	private double g = 33e2;
	private float h = 0x1.fffep-12f;// e的p次方 即e的12次方
	// private float i=1.fffep-12f;
	private float i = 0x1.fffep-12f;
	private long p = 0b1_1_1_0_1;
	// private long q=0b1_1_1_0_2;//二进制表示没有2
	int a7 = 0x5_2; // 有效的 (16进制数字) 

	// int a5 = 0_x52; // 无效，不能在0x之间有下划线
	int a9 = 0_52; // 有效的（8进制数）
	int a10 = 05_2; // 有效的（8进制数）
	// int a11 = 052_; // 无效的，不能以下划线结尾

	public static void 打印() {
		int a = 1_2;// 在1.7中增强
		long b = 1l;
		System.out.println("中文方法哦");
	}

	public static void main(String[] args) {
		打印();
		test2();
		test3();
	}

	public void test() {
		/*
		 * int i1 = 3; int i2 = i1; i2 = 4; System.out.print("i1==" + i1);
		 * System.out.println(" but i2==" + i2); Value v1 = new Value(); v1.val = 5;
		 * Value v2 = v1; v2.val = 6; System.out.print("v1.val==" + v1.val);
		 * System.out.println(" and v2.val==" + v2.val);
		 */
	}

	/**
	 * 打印整数的二进制
	 */
	static void test2() {
		int a = -6;
		int b = 0x80000000;
		System.out.println(b);
		// 因为整数32位 按位取32次
		for (int i = 0; i < 32; i++) {
			// 0x80000000 代表最高位为1即负数
			/*
			 * <<:左移运算符，num << 1,相当于num乘以2 >>:右移运算符，num >> 1,相当于num除以2
			 * >>>:无符号右移，忽略符号位，空位都以0补齐 位异或运算（^）:两个数转为二进制，然后从高位开始比较，如果相同则为0，不相同则为1。
			 * 位与运算符（&）:两个数都转为二进制，然后从高位开始比较，如果两个数都为1则为1，否则为0。
			 * 位或运算符（|）:两个数都转为二进制，然后从高位开始比较，两个数只要有一个为1则为1，否则就为0。
			 * 位非运算符（~）:如果位为0，结果是1，如果位为1，结果是0.
			 */
			int t = (a & 0x80000000 >>> i) >>> (31 - i);
			System.out.print(t);
		}
	}

	/**
	 * java中进制的转换
	 */
	static void test3() {
		System.out.println();
		System.out.println("把2,8,16的数字的字符串形式，转化为10进制：");
		System.out.println(Integer.parseInt("10", 10));
		System.out.println(Integer.parseInt("10", 2));
		System.out.println(Integer.parseInt("10", 8));
		System.out.println(Integer.parseInt("10", 16));
		System.out.println();

		System.out.println("把10进制，转化为2,8,16进制：");
		System.out.println(Integer.toString(10));
		System.out.println(Integer.toBinaryString(10));
		System.out.println(Integer.toOctalString(10));
		System.out.println(Integer.toHexString(10));
		System.out.println("把10进制，输出为8,16进制到字符串，而且可以控制输出形式：");
		String s;
		System.out.println(s = String.format("%d", 10));
		System.out.println(s = String.format("%o, %#o, %#4o, %#04o", 10, 10, 10, 10));
		System.out.println(s = String.format("%x, %#x, %#4x, %#04x", 10, 10, 10, 10));
		System.out.println();

	}
	/**
	 * jvm执行过程
	 */
	void test4() {
		int i;
		for (i = 0; i < 100; i++) {//0   iconst_0  // Push int constant 0   0入栈
			//1   istore_1       // Store into local variable 1 (i=0)  弹出赋值给变量i
			//2   goto 8         // First time through don't increment 跳转到第8步
			//8   iload_1        // Push local variable 1 (i)吧i和100入栈
			//9   bipush 100     // Push int constant 100
			//11  if_icmplt 5    // Compare and loop if less than (i < 100)  //比较变量i和100
			//成立则执行5   iinc 1 1       // Increment local variable 1 by 1 (i++)  变量自增




			;
			// Loop body is empty
		}
	}

}
