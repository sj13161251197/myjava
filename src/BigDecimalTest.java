import java.math.BigDecimal;

public class BigDecimalTest {
	public static void main(String[] args) {
		int a=1;
		int b=1;
		BigDecimal bigDecimal = new BigDecimal(a+b);
		BigDecimal bigDecimal2 = new BigDecimal(a+b+b);
		//2/3
		System.out.println(bigDecimal.divide(bigDecimal2, 2,BigDecimal.ROUND_DOWN).toString());
		System.out.println(bigDecimal.divide(bigDecimal2,2, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_UP));
		System.out.println(bigDecimal.divide(bigDecimal2,4, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP));
	}
}
 