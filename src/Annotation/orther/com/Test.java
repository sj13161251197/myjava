package Annotation.orther.com;
@AnnotationXueXi//注解Table定义   能够使用范围：用于注解类、接口(包括注解类型) 或enum声明
public class Test {
	@NoDBColumn//能够使用范围：仅可用于注解类的成员变量。
	@Column
	private int a;
	public static void main(String[] args) {
		
	}
	public void aa() {
		
	}
}
