package Annotation.orther.com;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;  
import java.lang.annotation.ElementType;  
import java.lang.annotation.Inherited;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;
import java.lang.reflect.Method;  
/**
 * ���ע��
 * @author Administrator
 *TestΪʹ��ע�����
 *TestAnnotationΪע��Ķ�����
 */
public class AnnoProcess {
	public static void main(String[] args) {
		TestAnnotation ma = Test.class.getAnnotation(TestAnnotation.class);
	    System.out.println(ma.value());

	    // ��ȡ����ʹӸ���̳е�ע��
	    //Annotation[] annotations = Test.class.getAnnotations();
	    // ����ȡ�����ע��
	    Annotation[] annotations = Test.class.getDeclaredAnnotations();
	}
}

