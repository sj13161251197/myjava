
public class A {
	/*
	 * ctrlword读卡的控制字，ctrlword是一个字节，相当于八个位，每个位只有0和1两种状态：
	 * 字节0：块0可读性，
	 * 字节1：块1可读性，
	 * 字节2：块2可读性，
	 * 字节3：1-仅读指定序列号的卡操作/0-对所有卡进行操作
	 * 字节4：1-在函数调用时需要每次指定密码进行认证，0-启用芯片内部密码进行认证
	unsigned char ctrlword,
	
	
	unsigned char *serial,
	
	//area是需要读出的区号，则0-15中的某个数。
	unsigned char area,
	
	//keyA1B0为0时以B密码来认证，为非0时以A密码来认证。刚出厂的卡以A密码来认证。
	unsigned char keyA1B0,
	
	//*picckey 指向存放卡密码的数组(6个char的密码数组)。
	unsigned char *picckey,
	
	//piccdata0_2是指向下标个数大于48的字节数组，用于存放3个块的数据，
	//其中下标0~15存放作为读出的块0的数据，
	//下标16~31存放作为读出的块1的数据，
	//下标32~47存放作为读出的块2的数据，
	unsigned char *piccdata0_2
	*/
	public static void main(String[] args) {
		int i=0;
		//System.out.println(i++);
		//System.out.println(i++); 
		i=++i;
		System.out.println(i);
		i=++i;
		System.out.println(i);
		System.out.println(i);
				
	}
}
