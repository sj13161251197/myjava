package net;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class TimeoutTaskUtils {
	/** 
     * @param args 
     */  
    public static void main(String[] args) {  
    	 System.out.println( callMethod(new TimeoutTaskUtils(), "call" , new Class<?>[]{Integer.class}, new Object[]{ 1523 } ) ); 
        Test1();  
    }
    /**
     * 测试方法1(把方法通过继承的方式实现检查超时)
     */
	private static void Test1() {
		boolean result = TimeoutTaskUtils.execute(new MyTimeoutTask(), 5);  
        System.out.println("result = " + result);  
        System.out.println("-- finished. --");
	}  
	/** 
     * 执行一个有时间限制的任务 
     * @param task    待执行的任务 
     * @param seconds 超时时间(单位: 秒) 
     * @return 
     */  
    public static Boolean execute(Callable<Boolean> task, int seconds) {  
        Boolean result = Boolean.FALSE;  
        ExecutorService threadPool = Executors.newCachedThreadPool();  
          
        try  {  
            Future<Boolean> future = threadPool.submit(task);  
            result = future.get(seconds, TimeUnit.SECONDS);  
        }catch (Exception e){  
            result = Boolean.FALSE;  
            e.printStackTrace();  
        }finally{  
            threadPool.shutdownNow();  
        }  
          
        return result;  
    } 
    /*** 
     * 方法参数说明 
     * @param target 调用方法的当前对象 
     * @param methodName 方法名称 
     * @param parameterTypes 调用方法的参数类型 
     * @param params 参数  可以传递多个参数 
     *  
     * */  
    public static Object callMethod(final Object target , final String methodName ,final Class<?>[] parameterTypes,final Object[]params){  
        ExecutorService executorService = Executors.newSingleThreadExecutor();    
        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {    
            public String call() throws Exception {   
                String value = null  ;   
                try {  
                    Method method = null ;   
                    method = target.getClass().getDeclaredMethod(methodName , parameterTypes ) ;    
                      
                    Object returnValue = method.invoke(target, params) ;    
                    value = returnValue != null ? returnValue.toString() : null ;  
                } catch (Exception e) {  
                    e.printStackTrace() ;  
                    throw e ;   
                }  
                return value ;  
            }    
        });    
            
        executorService.execute(future);    
        String result = null;    
        try{  
            /**获取方法返回值 并设定方法执行的时间为10秒*/  
            result = future.get(10 , TimeUnit.SECONDS );    
              
        }catch (InterruptedException e) {    
            future.cancel(true);    
            System.out.println("方法执行中断");   
        }catch (ExecutionException e) {    
            future.cancel(true);    
            System.out.println("Excuti on异常");    
        }catch (TimeoutException e) {    
            future.cancel(true);    
            throw new RuntimeException("invoke timeout" , e );  
        } catch (java.util.concurrent.TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			 executorService.shutdownNow();   
		}  
       
        return result ;  
    }  
    public Object call(Integer id){  
        try {  
            Thread.sleep( 11000 ) ;   
        } catch (Exception e) {  
        }  
        return id ;   
    } 
}
class MyTimeoutTask implements Callable<Boolean> {  
    
    @Override  
    public Boolean call() throws Exception {  
        for (int i=0; i<10; i++) {  
            System.out.println("i = " + i);  
            Thread.sleep(1000);  
        }  
        return true;  
    }  
} 
