package 进制转换;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
 
public class To2_4_8_16_10 {
	
	private static char[] array = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
            .toCharArray();
    private static String numStr = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static void main(String[] args) {
		
		System.out.println((char)Integer.parseInt("65"));
		
		System.out.println('A'==((char)Integer.parseInt("65")));
		
		
		
		
	/*	Map<String,Object> obj=new HashMap<String,Object>();
	obj.put("sss", "ff");
	System.out.println(obj.toString());
	System.out.println("sdsdsdd".contains("sd"));
	System.out.println("sdsdsdd".contains("sd1"));
	System.out.println("sd1sdsdd".indexOf("sds"));
	System.out.println("sdsdsdd".startsWith("sds"));*/
		/*int a=1;
		System.out.println(1==0x01);//进制比较
		System.out.println(10==0xA);//进制比较
		long n_to_10 = N_to_10("1a0",16);
		System.out.println(n_to_10); 
		int ab=0xff;
		System.out.println(Integer.toHexString(ab));//整数以16进制表示
		System.out.println(Integer.toBinaryString(7));//整数以2进制表示*/
		/*String card="ae93be79";
		String card16=card.substring(6, 8)+card.substring(4, 6)+card.substring(2, 4)+card.substring(0, 2);
		System.out.println(Integer.parseInt(card16,16));*/
		String card="d523ba0a";
		
		//System.out.println(Long.parseLong(card,16));
		//System.out.println(stringToNlen("da2ab",10)); 
		/*String card0="";
		for(int i=0;i<(9-card.length());i++) {
			card0+="0";
		}
		card=card0+card;
		String card16=card.substring(6, 8)+card.substring(4, 6)+card.substring(2, 4)+card.substring(0, 2);
		System.out.println(Integer.parseInt(card16,16));*/
	}
	public static String stringToNlen(String cardNum,int n){
		if(cardNum.length()>=n) {
			return cardNum;
		}else {
			String str0="";
			for(int i=0;i<n-cardNum.length();i++) {
				str0+="0";
			}
			cardNum=str0+cardNum;
		}
		return cardNum;
	}
	/**
	 * 进制的表达
	 * 2进制  只有0和1组成 　　　　　　   如:010101
	 * 8进制  以0开头，0~7组成 　　 　　  如:012345
	 * 10进制 以1~9开头，0~9组成    　　  如:100
	 * 16进制 以0X开头，0~9或者a~f组成   如:0x12c
	 * @param number
	 * @param N
	 * @return
	 */
	public void printOut(){
		
	}
    //10进制转为其他进制，除留取余，逆序排列
    public static String _10_to_N(long number, int N) {
        Long rest = number;
        Stack<Character> stack = new Stack<Character>();
        StringBuilder result = new StringBuilder(0);
        while (rest != 0) {
            stack.add(array[new Long((rest % N)).intValue()]);
            rest = rest / N;
        }
        for (; !stack.isEmpty();) { 
            result.append(stack.pop());
        }
        return result.length() == 0 ? "0":result.toString();

    }

    // 其他进制转为10进制，按权展开
    public static long N_to_10(String number, int N) {
        char ch[] = number.toCharArray();
        int len = ch.length;
        long result = 0;
        if (N == 10) {
            return Long.parseLong(number);
        }
        long base = 1;
        for (int i = len - 1; i >= 0; i--) {
            int index = numStr.indexOf(ch[i]);
            result += index * base;
            base *= N;
        }

        return result;
    }
}
