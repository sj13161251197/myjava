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
*�Զ���ע�⣺

����1.ʹ��@interface�Զ���ע��ʱ���Զ��̳���java.lang.annotation.Annotation�ӿڣ�
		�ɱ�������Զ��������ϸ�ڡ��ڶ���ע��ʱ��
		���ܼ̳�������ע���ӿڡ�@interface��������һ��ע�⣬
		���е�ÿһ������ʵ������������һ�����ò��������������ƾ��ǲ��������ƣ�
		����ֵ���;��ǲ��������ͣ�����ֵ����ֻ���ǻ������͡�Class��String��enum����
		����ͨ��default������������Ĭ��ֵ��
	2.����ע���ʽ��
����		public @interface ע���� {������}

	3.ע������Ŀ�֧���������ͣ�

��������1.���л����������ͣ�int,float,boolean,byte,double,char,long,short)
��������2.String����
��������3.Class����
��������4.enum����
��������5.Annotation����
��������6.�����������͵�����
	4.�������ù淶
		��һ,ֻ����public��Ĭ��(default)����������Ȩ����.����,String value();����ѷ�����ΪdefaulĬ�����ͣ��� ��
����		�ڶ�,������Աֻ���û�������byte,short,char,int,long,float,double,boolean���ֻ����������ͺ� String,Enum,Class,annotations����������,�Լ���һЩ���͵�����.����,String value();����Ĳ�����Ա��ΪString;����
����		����,���ֻ��һ��������Ա,��ðѲ���������Ϊ"value",���С����.��:���������FruitNameע���ֻ��һ��������Ա��

	5.ע��Ԫ�ص�Ĭ��ֵ��

����		ע��Ԫ�ر�����ȷ����ֵ��Ҫô�ڶ���ע���Ĭ��ֵ��ָ����Ҫô��ʹ��ע��ʱָ����
		�ǻ������͵�ע��Ԫ�ص�ֵ����Ϊnull�����, ʹ�ÿ��ַ�����0��ΪĬ��ֵ��һ�ֳ��õ�������
		���Լ��ʹ�ô��������ѱ���һ��Ԫ�صĴ��ڻ�ȱʧ��״̬��
		��Ϊÿ��ע��������У�����Ԫ�ض����ڣ����Ҷ�������Ӧ��ֵ��
		Ϊ���ƿ����Լ��������ֻ�ܶ���һЩ�����ֵ��������ַ������߸�����һ�α�ʾĳ��Ԫ�ز����ڣ��ڶ���ע��ʱ�����Ѿ���Ϊһ��ϰ���÷�
 */
/**
 * 
 * @author Administrator
 *ֻ��һ������  �������ƽ���Ϊvalue()
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
     * ��ɫö��
     * @author peida
     *
     */
    public enum Color{ BULE,RED,GREEN};//.ע��Ԫ�ص�Ĭ��ֵ��
    
    /**
     * ��ɫ����
     * @return
     */
    Color fruitColor() default Color.GREEN;//.ע��Ԫ�ص�Ĭ��ֵ��
    /**
     * ��Ӧ�̱��
     * @return
     */
    public int id() default -1;//.ע��Ԫ�ص�Ĭ��ֵ��
    
    /**
     * ��Ӧ������
     * @return
     */
    public String name() default "";
    
    /**
     * ��Ӧ�̵�ַ
     * @return
     */
    public String address() default "";

}
/**
 * ע�⴦����  4����
 * @author Administrator
 *1. ��Ҫ�̳�AbstractProcessor������дprocess������ 
 *2.�����˴�������֧�ִ����ע���� 
 *3.����ע�����õ�java�汾
 *4.�����Զ���Processor��������ͨ�������jar���ڱ�������е��õġ�
 *		Ϊ����java������ʶ�������Զ����Processor����Ҫ����META-INF�е��ļ���������Զ��������ע���ȥ�� 
 *
 */
@SupportedAnnotationTypes({"com.TestAnnotation"}) //�����˴�������֧�ִ����ע���� 
@SupportedSourceVersion(SourceVersion.RELEASE_8)//����ע�����õ�java�汾
 class MyProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    	 System.out.println("Test log in MyProcessor.process");
         System.out.println(roundEnv.toString());

         for (TypeElement typeElement : annotations) {    // ����annotations��ȡannotation����
             for (Element element : roundEnv.getElementsAnnotatedWith(typeElement)) {    // ʹ��roundEnv.getElementsAnnotatedWith��ȡ���б�ĳһ����ע���ע��Ԫ�أ����α���
                 // ��Ԫ���ϵ��ýӿڻ�ȡע��ֵ
                 int annoValue = element.getAnnotation(TestAnnotation.class).value();
                 String annoWhat = element.getAnnotation(TestAnnotation.class).what();

                 System.out.println("value = " + annoValue);
                 System.out.println("what = " + annoWhat);

                 // ��ǰ�������warning��Ϣ
                 processingEnv.getMessager().printMessage(Kind.WARNING, "value = " + annoValue + ", what = " + annoWhat, element);
             }
         }
        return false;
    }


}
/**
 * �Զ���ע��
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
