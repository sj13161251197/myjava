package Annotation.orther.com;

import java.lang.annotation.Target;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
/**
*自定义注解：

　　1.使用@interface自定义注解时，自动继承了java.lang.annotation.Annotation接口，
		由编译程序自动完成其他细节。在定义注解时，
		不能继承其他的注解或接口。@interface用来声明一个注解，
		其中的每一个方法实际上是声明了一个配置参数。方法的名称就是参数的名称，
		返回值类型就是参数的类型（返回值类型只能是基本类型、Class、String、enum）。
		可以通过default来声明参数的默认值。
	2.定义注解格式：
　　		public @interface 注解名 {定义体}

	3.注解参数的可支持数据类型：

　　　　1.所有基本数据类型（int,float,boolean,byte,double,char,long,short)
　　　　2.String类型
　　　　3.Class类型
　　　　4.enum类型
　　　　5.Annotation类型
　　　　6.以上所有类型的数组
	4.参数设置规范
		第一,只能用public或默认(default)这两个访问权修饰.例如,String value();这里把方法设为defaul默认类型；　 　
　　		第二,参数成员只能用基本类型byte,short,char,int,long,float,double,boolean八种基本数据类型和 String,Enum,Class,annotations等数据类型,以及这一些类型的数组.例如,String value();这里的参数成员就为String;　　
　　		第三,如果只有一个参数成员,最好把参数名称设为"value",后加小括号.例:下面的例子FruitName注解就只有一个参数成员。

	5.注解元素的默认值：

　　		注解元素必须有确定的值，要么在定义注解的默认值中指定，要么在使用注解时指定，
		非基本类型的注解元素的值不可为null。因此, 使用空字符串或0作为默认值是一种常用的做法。
		这个约束使得处理器很难表现一个元素的存在或缺失的状态，
		因为每个注解的声明中，所有元素都存在，并且都具有相应的值，
		为了绕开这个约束，我们只能定义一些特殊的值，例如空字符串或者负数，一次表示某个元素不存在，在定义注解时，这已经成为一个习惯用法
 */
/**
 * 
 * @author Administrator
 *只有一个参数  参数名称建议为value()
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
 @interface Definition1 {
    String value() default "";

}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface FruitColor {
    /**
     * 颜色枚举
     * @author peida
     *
     */
    public enum Color{ BULE,RED,GREEN};//.注解元素的默认值：
    
    /**
     * 颜色属性
     * @return
     */
    Color fruitColor() default Color.GREEN;//.注解元素的默认值：
    /**
     * 供应商编号
     * @return
     */
    public int id() default -1;//.注解元素的默认值：
    
    /**
     * 供应商名称
     * @return
     */
    public String name() default "";
    
    /**
     * 供应商地址
     * @return
     */
    public String address() default "";

}
/**
 * 注解处理器  4步骤
 * @author Administrator
 *1. 需要继承AbstractProcessor，并覆写process方法。 
 *2.声明此处理器所支持处理的注解类 
 *3.声明注解作用的java版本
 *4.由于自定义Processor类最终是通过打包成jar，在编译过程中调用的。
 *		为了让java编译器识别出这个自定义的Processor，需要配置META-INF中的文件，将这个自定义的类名注册进去。 
 *
 */
@SupportedAnnotationTypes({"com.TestAnnotation"}) //声明此处理器所支持处理的注解类 
@SupportedSourceVersion(SourceVersion.RELEASE_8)//声明注解作用的java版本
 class MyProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    	 System.out.println("Test log in MyProcessor.process");
         System.out.println(roundEnv.toString());

         for (TypeElement typeElement : annotations) {    // 遍历annotations获取annotation类型
             for (Element element : roundEnv.getElementsAnnotatedWith(typeElement)) {    // 使用roundEnv.getElementsAnnotatedWith获取所有被某一类型注解标注的元素，依次遍历
                 // 在元素上调用接口获取注解值
                 int annoValue = element.getAnnotation(TestAnnotation.class).value();
                 String annoWhat = element.getAnnotation(TestAnnotation.class).what();

                 System.out.println("value = " + annoValue);
                 System.out.println("what = " + annoWhat);

                 // 向当前环境输出warning信息
                 processingEnv.getMessager().printMessage(Kind.WARNING, "value = " + annoValue + ", what = " + annoWhat, element);
             }
         }
        return false;
    }


}
/**
 * 自定义注解
 * @author Administrator
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
 @interface TestAnnotation {
    int value();
    String what();
}
public class Definition{
	@TestAnnotation(value = 5, what = "This is a test")
    public static String msg = "Hello world!";
	public static void main(String[] args) {
		System.out.println(msg); 
	}
	public void test() {
		System.out.println("cshi");
	}
}
