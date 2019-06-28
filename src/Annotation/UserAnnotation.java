package Annotation;
//@AnnotationTest("red")////特殊的   使用时如果只有一个value属相可以不写=
//@AnnotationTest(color = "red", value = "twst")
//@AnnotationTest(color = "red", value = "twst",arr={4,5,6,7})//数组里边如果只有一个属相可以省略{}  如arr=4
@AnnotationTest(anno=@Anno("test"),color = "red", value = "twst",arr={4,5,6,7})//数组里边如果只有一个属相可以省略{}  如arr=4
public class UserAnnotation {

} 
