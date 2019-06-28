package reflect;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.function.IntBinaryOperator;

import enumTest.EnumTest;

/**
 * 反射类Class描述java 中class类的一个对象
 * @author Administrator
 *
 */
public class ReflectTest {
	public static void main(String[] args) throws Exception {
		ReflectTest re=new ReflectTest();
		re.javaBeanGetProp(); 
		//re.refArr(re); 
		//Point.main(new String[]{});
		/**
		 * 用反射的方式调用main
		 */
		/*Method method = Point.class.getMethod("main", String[].class);
		method.invoke(null,new Object[]{ new String[]{"aa","vv"}});//数组和一个参数的传参问题
		method.invoke(null,(Object)new String[]{"aa","vv"});*/
	}
	/**
	 * 构造的基础
	 * @throws ClassNotFoundException 
	 */
	public void ReflectBase() throws ClassNotFoundException{
		Class<String> c1=String.class;//Date 在内存中的字节码
		Class<? extends String> c2= new String("sss").getClass();
		//因为第三种是以字符窜的形式传参  所有用的更加多
		Class<?> c3= Class.forName("java.lang.String");//如果java 虚拟机内部含没有这个字节码需要先用类加载器把之加载到虚拟机中
		//八大基本类型+void
		
		System.out.println(c1.isPrimitive());//表示这个对象不是基本类型
		System.out.println(int.class==Integer.TYPE);//表示这个对象不是基本类型   基本类型可以用.TYPE
	}
	/**
	 * 反射的构造方法的使用和使用场合
	 */
	public void ReflectCon() throws Exception{
		Class<?> strClass= Class.forName("java.lang.String");
		Constructor<?> cons=strClass.getConstructor(String.class);
		System.out.println(cons.newInstance("aaa"));//通过构造对象创建一个对象
		System.out.println(cons.getName());
		
		//return c;
	}
	/**
	 * 反射的常用变量的使用和使用场合
	 * 一般情况下成员变量point  是共有的可以反射
	 */
	public void ReflectPoint() throws Exception{
		EnumTest point = new EnumTest();
		Class<? extends EnumTest> class1 = point.getClass(); 
		Field field = class1.getField("a");
		field.setAccessible(true);//是private 失效
		System.out.println(field.get(point));
		
		
		Point point2 = new Point();
		Class<? extends Point> class2 = point2.getClass(); 
		Field[] fields =class2.getFields();
		for(Field f:fields){
			if(f.getType()==String.class){//字节码比较用==
				
			}
			System.out.println(f.getName());
			f.setAccessible(true);//是private 失效
			System.out.println(f.get(point2));
		}
		//return c;
	}
	/**
	 * 反射的方法的使用和使用场合
	 * 一般情况下成员变量point  是共有的可以反射
	 */
	public void ReflectMeth() throws Exception{
		Class<?> strClass= Class.forName("java.lang.String");
		Method method = strClass.getMethod("charAt", int.class);
		System.out.println(method.invoke("aasv", 2));//"aasv".charAt(2)
		System.out.println(method.invoke("aasv", new Object[]{2}));//"aasv".charAt(2)
		Method[] meth=strClass.getMethods();
		for(Method m:meth){
			
		}
		//return c;
	}
	/**
	 * 数组的反射
	 */
	public void refArr(Object obj){
		System.out.println(new int[]{1,2,4});
		System.out.println(new String[]{"1","2","4"});
		
		/**
		 * 一下这2个的区别String属于Object对象 所以可以转换成功但是int数组不属于Object所以转换长一个List对象里边含一个int数组的对象
		 */
		System.out.println(Arrays.asList(new int[]{1,2,4}));
		System.out.println(Arrays.asList(new String[]{"1","2","4"}));
		if(obj.getClass().isArray()){
			int length = Array.getLength(obj);
			for(int i=0;i<length;i++){
				System.out.println(Array.get(obj, i));
				
			}
		}else{
			
		}
	}
	/**
	 * 类加载器
	 * @throws IOException 
	 */
	@SuppressWarnings("resource")
	public void classLoad() throws IOException {
		//完整路径
		//FileInputStream fileInputStream = new FileInputStream("config.properties");
		
		InputStream fileInputStream = ReflectTest.class.getClassLoader().getResourceAsStream("com/config.properties");
		Properties properties = new Properties();
		properties.load(fileInputStream);
		fileInputStream.close();
		String property = properties.getProperty("className");
		System.out.println(property);
	}
	/**
	 * javaBean
	 * @throws IntrospectionException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public void javaBeanGetProp() throws IOException, IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Point point = new Point();
		PropertyDescriptor pd=new PropertyDescriptor("x",point.getClass());//属相描述符
		
			Object invoke = pd.getReadMethod().invoke(point);
			System.out.println(invoke);
			
		//Map map={age:"1",name:"ass"};	
			
		BeanInfo beanInfo = Introspector.getBeanInfo(point.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor p:propertyDescriptors){ 
			System.out.println(p.getName());
		}
	}
}
class Point{
	public int x=5;
	public int y=6;
	public static void main(String[] args) {
		System.out.println(55);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
