package proxy;

import java.lang.reflect.InvocationHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

import java.util.List;


/**
 * 代理类和目标类时限相同类
 * @author Administrator
 *采用工程模式和配置文件的方式进行管理
 */
public class ProxyTest {
	public static void main(String[] args) throws Exception{
		/*Class<?> proxyClass = Proxy.getProxyClass(List.class.getClassLoader(), List.class);
		Proxy.newProxyInstance(
				List.class.getClassLoader(),
				new Class[]{List.class},
				new InvocationHandler(){

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// TODO Auto-generated method stub
						return null;
					}
					
				});
		Constructor<?>[] constructors = proxyClass.getConstructors();
		for(Constructor con:constructors){
			String name = con.getName();
			StringBuilder stringBuffer = new StringBuilder(name);
			stringBuffer.append('(');
			Class[] parameterTypes = con.getParameterTypes();
			for(Class pa:parameterTypes){
				stringBuffer.append(pa.getName()).append(",");
			}
			if(parameterTypes!=null&&parameterTypes.length>0){
				stringBuffer.deleteCharAt(stringBuffer.length()-1);
			}
			
			stringBuffer.append(')');
			System.out.println(stringBuffer.toString());
		}
		 
		
		 Method[] methods = proxyClass.getMethods();
		for(Method meth:methods){
			String name = meth.getName();
			StringBuilder stringBuffer = new StringBuilder(name);
			stringBuffer.append('(');
			Class[] parameterTypes = meth.getParameterTypes();
			for(Class pa:parameterTypes){
				stringBuffer.append(pa.getName()).append(",");
			}
			if(parameterTypes!=null&&parameterTypes.length>0){
				stringBuffer.deleteCharAt(stringBuffer.length()-1);
			}
			
			stringBuffer.append(')');
			System.out.println(stringBuffer.toString());
		}*/
		final ArrayList target=new ArrayList();//方法的内部类要访问成员属相   这个属相必须是final属相
		List newInstance = (List) getProxy(target,new Advice(){

			@Override
			public void beforeMeth() {
				// TODO Auto-generated method stub
				System.out.println("之前");
			}

			@Override
			public void afterMeth() {
				// TODO Auto-generated method stub
				System.out.println("之后");
			}

			
		});
		newInstance.add("aa");
		newInstance.toString();
		
	}
	/**
	 * 动态代理设计  参数目标:  advie建议
	 * @param target
	 * @param advice
	 * @return
	 * @throws Exception
	 */
	private static Object getProxy(Object target,Advice advice)throws Exception{
		Object newProxyInstance = Proxy.newProxyInstance(
				target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				new InvocationHandler(){

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// TODO Auto-generated method stub
						
						advice.beforeMeth();
						//try{
							Object invoke = method.invoke(target, args);
						//}catch(Exception e){
							//advice.tryMeth();
						//}
						advice.afterMeth();
						return invoke;
					}
					
				});
		return newProxyInstance;
	}
	
}
interface Advice{
	void beforeMeth();
	void afterMeth();
	//void tryMeth();
	//void writerMeth();
}

