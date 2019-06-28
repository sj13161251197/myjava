package GenericTest;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.sun.org.apache.regexp.internal.recompile;

public class GenericTest {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		GenericTest genericTest = new GenericTest();
		/**
		 * T 泛型类型推断
		 */
		Integer add = genericTest.add(5,6);
		Number add2 = genericTest.add(5,6.5);
		//genericTest.swip(new String[]{"1","3","5","7"}, 2, 3);
		
		//genericTest.swip(new int[]{1,2,3,5,7,9}, 2, 3);//泛型的实际类型必须是引用类型  int 不是
		Integer aotuT = genericTest.aotuT(5);
		String aotuT2 = genericTest.aotuT("aaa");
		
		//类上的泛型（如果类中多个方法都要使用泛型用类级别的泛型）
		//Dao<GenericTest> dao = new Dao<GenericTest>();
		//dao.add(new GenericTest());
		genericTest.getApplyVector();
	}
	/**
	 * 泛型类型的定义
	 * @param a
	 * @param b 
	 * @return
	 */
	public <T extends Number>T add(Number a,Number b){
		return null;
	}
	/**
	 * 交换数组的某2个元素的位置
	 */
	public <T>T[] swip(T[] arr,int a,int b){
		T c=arr[a];
		arr[a]=arr[b];
		arr[b]=c;
		return arr;
	}
	/**
	 * 类型自动转换
	 */
	public <T>T aotuT(T obj){
		return obj;
	}
	/**
	 * 获取方法的泛型的类型
	 */
	public static void applyVector(Vector<Date> list){
		
	}
	public static void getApplyVector() throws NoSuchMethodException, SecurityException{
		Method method = GenericTest.class.getMethod("applyVector", Vector.class);
		Type[] genericParameterTypes = method.getGenericParameterTypes();//获取所有的参数的泛型类型
		ParameterizedType ptype = (ParameterizedType)genericParameterTypes[0];
		System.out.println(ptype.getTypeName());
		System.out.println(ptype.getRawType());
		System.out.println(ptype.getActualTypeArguments()[0]);
	}
}
/**
 * 在类上使用泛型
 * @author Administrator
 *模拟dao层数据操作
 * @param <E>
 */
class Dao<E>{//如果类中多个方法都要使用泛型用类级别的泛型）
	public E queryById(String id){
		return null;
	}
	public E queryByEntity(E entity){
		return null;
		
	}
	public void add(E entity){
		//return null;
		
	}
	public void edit(E entity){
		//return null;
		
	}
	public void deleteById(String id){
		//return null;
		
	}
	public void delete(E entity){
		//return null;
		
	}
	public List<E> queryList(E entity){
		return null;
	}
}
