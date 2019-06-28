package Annotation;



public class JiexiAnnotation {
	public static void main(String[] args) {
		boolean annotationPresent = UserAnnotation.class.isAnnotationPresent(AnnotationTest.class);
		if(UserAnnotation.class.isAnnotationPresent(AnnotationTest.class)){//如果UserAnno这个类有注解AnnotationTest
			AnnotationTest annotation = UserAnnotation.class.getAnnotation(AnnotationTest.class);
			System.out.println(annotation.color());
			System.out.println(annotation.arr().length);
		}
		System.out.println("******************");
	}
}
/*@FieldTypeAnnotation(type = "class", hobby = { "smoke" })  
 class ReflectAnnotation {  
    *//** 
     * leip 2016年12月3日 TODO 
     **//*  
    @FieldTypeAnnotation(hobby = { "sleep", "play" })  
    private String maomao;  
  
    @FieldTypeAnnotation(hobby = { "phone", "buy" }, age = 27, type = "normal")  
    private String zhangwenping;  
      
    @MethodAnnotation()  
    public void method1() {  
          
    }  
    @MethodAnnotation(desc="method2")  
    public void method2() {  
          
    }  
  
    public static void main(String[] args) {  
        // 此处要用反射将字段中的注解解析出来  
        Class<ReflectAnnotation> clz = ReflectAnnotation.class;  
        // 判断类上是否有次注解  
        boolean clzHasAnno = clz.isAnnotationPresent(FieldTypeAnnotation.class);  
        if (clzHasAnno) {  
            // 获取类上的注解  
            FieldTypeAnnotation annotation = clz.getAnnotation(FieldTypeAnnotation.class);  
            // 输出注解上的属性  
            int age = annotation.age();  
            String[] hobby = annotation.hobby();  
            String type = annotation.type();  
            System.out.println(clz.getName() + " age = " + age + ", hobby = " + Arrays.asList(hobby).toString() + " type = " + type);  
        }  
        // 解析字段上是否有注解  
        // ps：getDeclaredFields会返回类所有声明的字段，包括private、protected、public，但是不包括父类的  
        // getFields:则会返回包括父类的所有的public字段，和getMethods()一样  
        Field[] fields = clz.getDeclaredFields();  
        for(Field field : fields){  
            boolean fieldHasAnno = field.isAnnotationPresent(FieldTypeAnnotation.class);  
            if(fieldHasAnno){  
                FieldTypeAnnotation fieldAnno = field.getAnnotation(FieldTypeAnnotation.class);  
                //输出注解属性  
                int age = fieldAnno.age();  
                String[] hobby = fieldAnno.hobby();  
                String type = fieldAnno.type();  
                System.out.println(field.getName() + " age = " + age + ", hobby = " + Arrays.asList(hobby).toString() + " type = " + type);  
            }  
        }  
        //解析方法上的注解  
        Method[] methods = clz.getDeclaredMethods();  
        for(Method method : methods){  
            boolean methodHasAnno = method.isAnnotationPresent(MethodAnnotation.class);  
            if(methodHasAnno){  
                //得到注解  
                MethodAnnotation methodAnno = method.getAnnotation(MethodAnnotation.class);  
                //输出注解属性  
                String desc = methodAnno.desc();  
                System.out.println(method.getName() + " desc = " + desc);  
            }  
        }  
    }  
}  */
