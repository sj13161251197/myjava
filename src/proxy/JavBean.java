package proxy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
/**
 * 通过代理技术模拟spring  javaBean 技术
 * @author Administrator
 *
 */
public class JavBean {
	public static void main(String[] args) throws Exception{
		InputStream resourceAsStream = JavBean.class.getResourceAsStream("config.properties");
		JavBean javBean = new JavBean(resourceAsStream);
		List list = (List)javBean.getBean("testL");
		
		list.add("ee");
	}
	Properties pro=new Properties();
	public  JavBean(){
		
	}
	public JavBean(InputStream inp) throws IOException{
		pro.load(inp);
	}
	public Object getBean(String name) throws Exception{
		String property = pro.getProperty(name);
		Class<?> forName;
		
		Object newInstance=null;
		try {
			forName = Class.forName(property);
			newInstance = forName.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//所有javaBean必须要有一个无参构造
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(newInstance instanceof List){
			ProxFactBean	proxy= new ProxFactBean();
			proxy.setAdvice(new Advice(){

				@Override
				public void beforeMeth() {
					// TODO Auto-generated method stub
					System.out.println("********************");
				}

				@Override
				public void afterMeth() {
					// TODO Auto-generated method stub
					System.out.println("----------------------------");
				}
				
			});
			//proxy.setTarget(newInstance);
			proxy.getProxy(newInstance,new Advice(){

				@Override
				public void beforeMeth() {
					// TODO Auto-generated method stub
					System.out.println("********************");
				}  

				@Override
				public void afterMeth() {
					// TODO Auto-generated method stub
					System.out.println("----------------------------");
				}
				
			});
		}else{ 
			
		}
		return newInstance; 
		
	}
}
/**
 * 代理工程类
 * @author Administrator
 *
 */
 class ProxFactBean {
	private  Object target;
	private  Advice advice;
	public  Object getProxy() {
		// TODO Auto-generated method stub
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
	public static Object getProxy(Object target,Advice advice)throws Exception{
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
	public Object getTarget() {
		return target;
	}
	public void setTarget(Object target) {
		this.target = target;
	}
	public Object getAdvice() {
		return advice;
	}
	public void setAdvice(Advice advice) {
		this. advice= advice;
	}
	
}
