package java8_lambda.Nashorn_jjs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Date;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import java8_lambda.lambda.Person;

public class JjsTest {
   public static void main(String args[]) throws FileNotFoundException, ScriptException, NoSuchMethodException{
	   //test1();
	   //test3();
	   sayHello("dsv");
	   System.err.println("***********************");
	   //Java中调用Nashorn，所以现在在Java代码中实现简单的
      ScriptEngineManager scriptEngineManager = new ScriptEngineManager(); 
      ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn"); 
      String name = "Mahesh"; 

      Integer result = null;
      try {
         nashorn.eval("print('" + name + "')");
         result = (Integer) nashorn.eval("10 + 2");   
      }catch(ScriptException e){
         System.out.println("Error executing script: "+ e.getMessage());
      }
      System.out.println(result.toString());
   } 
   static void test1() throws ScriptException, FileNotFoundException{
	   ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
	   engine.eval("print('Hello World!');");
	   engine.eval(new FileReader("sample.js"));
   }
   //你同样可以将脚本编译为Java字节码后调用，这样在多次调用的情况下效率会更高，例如：
   static void test2(){
	   try {
	   ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
	   CompiledScript compiledScript = ((Compilable) engine).compile("print('Hello World!');");
	   Bindings bindings = engine.createBindings();
	   bindings.put("name", "Nashorn");

	   engine.eval("print('Hello ' + name);");
		engine.eval("print('"+compiledScript.toString()+"')");
	} catch (ScriptException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   //在Java中调用JavaScript函数
   @SuppressWarnings("resource")
static void test3() throws FileNotFoundException, ScriptException, NoSuchMethodException{
	   ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
	   File fileReader = new File("sample.js");
	 System.out.println(fileReader.getAbsolutePath());
	   engine.eval(new FileReader("sample.js"));
 
	   Invocable invocable = (Invocable) engine;

	   Object result = invocable.invokeFunction("fun1", "Nashorn");
	   System.out.println(result);
	   System.out.println(result.getClass());
	   
	   invocable.invokeFunction("fun2", new Date());
	// [object java.util.Date]

	invocable.invokeFunction("fun2", LocalDateTime.now());
	// [object java.time.LocalDateTime]

	invocable.invokeFunction("fun2", new Person());
	// [object my.package.Person]

   }
   public static String sayHello(String name) {
	    System.out.println("Hello " + name);
	    return "Hi!";
	}
}
