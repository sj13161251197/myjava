package JVM;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PermToOom {
	public static void main(String[] args)  {
		test(5);
        /*Random rnd = new Random();

        List<String> interned = new ArrayList<String>();

        for (;;) {

            int length = rnd.nextInt(100);

            StringBuilder builder = new StringBuilder();

            String chars ="abcdefghijklmnopqrstuvwxyz";

            for ( int i = 0; i < length; i++) {

               builder.append(chars.charAt(rnd.nextInt(chars.length())));

            }

           interned.add(builder.toString().intern());

        }*/

	}
	/**
	 * 帧栈溢出测试列子
	 * 溢出原理：一个方法调用时进栈  方法结束时出栈  这个方法死循环一直进栈导致栈满而溢出
	 * 操作数栈：java中无寄存器依靠操作数栈压栈保存区部变量
	 */
	public static int test(int a){
		return test(2*a);
	}
}
