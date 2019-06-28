package JVM;
/**
 * 语法：
 * 行终结符 \t  \f
 * 标识符
 * 关键字
 * @author Administrator
 * jvm  主要定义二进制。class和jvm指令集
 * class文件格式  数字的内部表示和存储   数据类型returnAddress
 * 定义pc  ,堆栈   方法区
 *
 */
public class JvmTest {
	int a=1_2;
	public static void main(String[] args) {
		
		// 整数和二进制的转换
		  JvmTest jvmTest = new JvmTest(); 
			System.out.println(jvmTest.a);
			System.gc();
			Test1 test1 = new Test1();
	}
	public void test(int b){
		//int b=-6;//0x80000000表示最高位为1的数字
		for(int i=0;i<32;i++){//整数32位   所以32次循环
			//&位与运算：两个数都都为真(或为1)，其结果为真，如果两位数中有一位为假(或为0）者结果为假
			//>>表示是带符号的右移
			//>>>表示无符号的右移
			int t=(b&0x80000000>>>i)>>>(31-i);
			System.out.println(t);
			
		}
	}
}
class Test1{
	void test(String a){
		System.out.println(a);
	}
}
class Test2{
	void test(String a){
		System.out.println(a);
	}
}
