package GenericTest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 泛型的使用
 * @author Administrator
 * @param <E>
 *
 */
public class GenericUse{
	//集合中存的对象取出来之后默认Object  为了确认取出来的对象是属于什么对象在存放的集合上添加泛型是集合只能存放泛型的类型
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		ArrayList<Integer> arr=new ArrayList<Integer>();
		arr.add(2);
		Integer integer = arr.get(0); 
		
		//编译时会去掉集合的泛型   集合的泛型只在编辑时起作用  如一下通过反射得到的字节码给集合添加
		arr.getClass().getMethod("add", Object.class).invoke(arr, "adv");
		System.out.println(arr.get(1));
		GenericUse genericUse = new GenericUse();
		genericUse.test_Rand(new ArrayList<Object>());
		
	}
	public  void testE(){
		//E:表示类型变量或者类型参数
		//<Integer>是参数化的类型
		ArrayList arrE=new ArrayList();//泛型读属于typeOf
		//参数化泛型与原始类型兼容
		ArrayList<Integer> arrI=new ArrayList();
		ArrayList arrI2=new ArrayList<Integer>();
		//ArrayList<Object> arrI3=new ArrayList<Integer>();但继承关系不承认
		
		
		
	}
	/**
	 * 泛型中的？通配符
	 */
	public  void test_Rand(ArrayList<?> arrE){
		/**
		 * 通配符？
		 */
		
		//E:表示类型变量或者类型参数
		//arrE=ArrayList<Integer>();
		//arrE.add("add");
		//arrE.add((Object)"add");
		//?通配符只能调用与参数类型无关的方法
		for(Object a:arrE){
			System.out.println(a);
		}
		
		
		//通配符上限限定
		ArrayList<? extends Number> arrI=new ArrayList<Integer>();
		//通配符下限限定
		ArrayList<? super Integer> arr2=new ArrayList<Number>();
		
		/**
		 * 通配符U
		 */
		
		
		/**
		 * 通配符T
		 */
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("aaa", "aaa");
		map.put("bbb", "bbb");
		Set<Map.Entry<String,String>> set=map.entrySet(); 
		for(Map.Entry<String,String> ent:set){
			System.out.println(ent.getKey()+":"+ent.getValue());
		}
	}
}
