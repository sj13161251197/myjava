package Annotation.orther.user;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import Annotation.orther.user.FruitColor.Color;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.RetentionPolicy;
/***********������***************/
public class FruitRun {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        FruitInfoUtil.getFruitInfo(Apple.class);
        
    }

}

/***********ע�⴦����***************/

 class FruitInfoUtil {
    public static void getFruitInfo(Class<?> clazz){
        
        String strFruitName=" ˮ�����ƣ�";
        String strFruitColor=" ˮ����ɫ��";
        String strFruitProvicer="��Ӧ����Ϣ��";
        
        Field[] fields = clazz.getDeclaredFields();
        
        for(Field field :fields){//����������
            if(field.isAnnotationPresent(FruitName.class)){//�����ϵ�ע���ע��Ա�
            	FruitName fruitName = (FruitName) field.getAnnotation(FruitName.class);
                strFruitName=strFruitName+fruitName.value();
                System.out.println(strFruitName);
            }
            else if(field.isAnnotationPresent(FruitColor.class)){
                FruitColor fruitColor= (FruitColor) field.getAnnotation(FruitColor.class);
                strFruitColor=strFruitColor+fruitColor.fruitColor().toString();
                System.out.println(strFruitColor);
            }
            else if(field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider fruitProvider= (FruitProvider) field.getAnnotation(FruitProvider.class);
                strFruitProvicer=" ��Ӧ�̱�ţ�"+fruitProvider.id()+" ��Ӧ�����ƣ�"+fruitProvider.name()+" ��Ӧ�̵�ַ��"+fruitProvider.address();
                System.out.println(strFruitProvicer);
            }
        }
    }
}

 /***********ע������***************/

 /**
  * ˮ������ע��
  * @author peida
  *
  */
 @Target(ElementType.FIELD)
 @Retention(RetentionPolicy.RUNTIME)
 @Documented
 @interface FruitName  {
     String value() default "";
 }

 /**
  * ˮ����ɫע��
  * @author peida
  *
  */
 @Target(ElementType.FIELD)
 @Retention(RetentionPolicy.RUNTIME)
 @Documented
  @interface FruitColor {
     /**
      * ��ɫö��
      * @author peida
      *
      */
     public enum Color{ BULE,RED,GREEN};
     
     /**
      * ��ɫ����
      * @return
      */
     Color fruitColor() default Color.GREEN;

 }

 /**
  * ˮ����Ӧ��ע��
  * @author peida
  *
  */
 @Target(ElementType.FIELD)
 @Retention(RetentionPolicy.RUNTIME)
 @Documented
  @interface FruitProvider {
     /**
      * ��Ӧ�̱��
      * @return
      */
     public int id() default -1;
     
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

 /***********ע��ʹ��***************/

  class Apple {
     
 	 @FruitName ("Apple")
     private String appleName;
     
     @FruitColor(fruitColor=Color.RED)
     private String appleColor;
     
     @FruitProvider(id=1,name="�����츻ʿ����",address="����ʡ�������Ӱ�·89�ź츻ʿ����")
     private String appleProvider;
     
     public void setAppleColor(String appleColor) {
         this.appleColor = appleColor;
     }
     public String getAppleColor() {
         return appleColor;
     }
     
     public void setAppleName(String appleName) {
         this.appleName = appleName;
     }
     public String getAppleName() {
         return appleName;
     }
     
     public void setAppleProvider(String appleProvider) {
         this.appleProvider = appleProvider;
     }
     public String getAppleProvider() {
         return appleProvider;
     }
     
     public void displayName(){
         System.out.println("ˮ���������ǣ�ƻ��");
     }
 }


