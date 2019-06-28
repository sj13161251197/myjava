package DataJG;

public class IntArr {
	public static void main(String[] args) {
		byte b=127;//定义范围-128-127   一个字节8个长度的2进制  首位符号位
		//byte b=129;
		byte b1=127;
		
		//结果257的原因
		System.out.println(b+b1);//byte类型的变量相加时，byte类型会自动转化为int类型再进行字面值的计算，计算的结果就是int类型了，因此我们需要将就算的结果强制转化为byte类型，在进行赋值的，
	
	
		short s=1;//15位
		short s1=32767;
		System.out.println(s+s1);//自动转int 
		
			int i=0xA;//32位
			int i1=0xa;//32位   16进制表示
			int i2=0b1010;//2进制的表现
			
		System.out.println(i+"/"+i1+"/"+i2);
		System.out.println(Integer.toHexString(i2));
		System.out.println(Integer.toBinaryString(i2));
		System.out.println(Integer.toOctalString(i2));
		long l=1L;
		
		float f=.1f;
		float f1=.01f;//32位
		double d=0.1;//64位
		
		char c='a';//保存一个字符  根据编码方式表示几个几个字节  unt   2个
		System.out.println(c);
		
		
		
		
		//运算  1自动转型2计算结果溢出不报错
		System.out.println(3/2);
		System.out.println(3/2.0);
		System.out.println(i++);//先输出在运算
		System.out.println(++i);//先运算在输出
		
		//移位
		int i3=5;//101
		int ii4=5;
		int ii5=ii4;
		ii5=6;
		System.out.println("*******************************"+ii4);
		System.out.println(i3<<10);//无符号←移动10位//1_010_000_000_000
		int i4=i3<<29;
		System.out.println(i4);//-1610612736   1010_0000_0000_0000_0000_0000_0000_0000
		int i5=i4>>29;
		int i6=i4>>>29;
		System.out.println(i5);//-3原因是  i4首位是1开头代表-1   10000000_00000000_00000000_00000101
														 //00000000_00000000_00000000_00000011
		System.out.println(Integer.toBinaryString(3));
		System.out.println(Integer.toBinaryString(i4));
		System.out.println(Integer.toBinaryString(i5));
		System.out.println(i6);
		System.out.println(Integer.toBinaryString(-3));
		System.out.println("*****************"+0b10000000_00000000_00000000_00000101);
	}
	public void arr() {
		//int 
	}
}
