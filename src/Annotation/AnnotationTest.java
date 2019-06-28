package Annotation;

import java.lang.annotation.Target;

import enumTest.EnumTest.WeekDay;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * http://www.cnblogs.com/peida/archive/2013/04/23/3036035.html
 * 1.注解概念:Annotion(注解)是一个特殊的java接口，程序可以通过反射来获取指定程序元素的Annotion对象，然后通过Annotion对象来获取注解里面的元数据。
 		 基本的规则：Annotation不能影响程序代码的执行，无论增加、删除 Annotation，代码都始终如一的执行
 	
 	2.元数据	可以用来创建文档，跟踪代码的依赖性，执行编译时格式检查，代替已有的配置文件。
 			Java中元数据以标签的形式存在于Java代码中，元数据标签的存在并不影响程序代码的编译和执行，它只是被用来生成其它的文件或针在运行时知道被运行代码的描述信息。
 			
 			1. 编写文档：通过代码里标识的元数据生成文档
　　　　		2. 代码分析：通过代码里标识的元数据对代码进行分析
　　　　		3. 编译检查：通过代码里标识的元数据让编译器能实现基本的编译检查	 

		第一，元数据以标签的形式存在于Java代码中。
　　　　第二，元数据描述的信息是类型安全的，即元数据内部的字段都是有明确类型的。
　　　　第三，元数据需要编译器之外的工具额外的处理用来生成其它的程序部件。
　　　　第四，元数据可以只存在于Java源代码级别，也可以存在于编译之后的Class文件内部。
 	3.注解的分类：

　　		根据注解参数的个数，我们可以将注解分为三类：
　　　　		1.标记注解:一个没有成员定义的Annotation类型被称为标记注解。这种Annotation类型仅使用自身的存在与否来为我们提供信息。比如后面的系统注解@Override;
　　　　		2.单值注解
　　　　		3.完整注解　　

　　		根据注解使用方法和用途，我们可以将Annotation分为三类：
　　　　		1.JDK内置系统注解
					@Override：用于修饰此方法覆盖了父类的方法;
　　　　				@Deprecated：用于修饰已经过时的方法;
　　　　				@SuppressWarnnings:用于通知java编译器禁止特定的编译警告。
						1.deprecation：使用了不赞成使用的类或方法时的警告；
　　　　					2.unchecked：执行了未检查的转换时的警告，例如当使用集合时没有用泛型 (Generics) 来指定集合保存的类型; 
　　　　					3.fallthrough：当 Switch 程序块直接通往下一种情况而没有 Break 时的警告;
　　　　					4.path：在类路径、源文件路径等中有不存在的路径时的警告; 
　　　　					5.serial：当在可序列化的类上缺少 serialVersionUID 定义时的警告; 
　　　　					6.finally：任何 finally 子句不能正常完成时的警告; 
　　　　					7.all：关于以上所有情况的警告。
　　　　		2.元注解
　　　　		3.自定义注解	 
 		 
 		 
 * 元注解：

　　元注解的作用就是负责注解其他注解。Java5.0定义了4个标准的meta-annotation类型，它们被用来提供对其它 annotation类型作说明。Java5.0定义的元注解：
　　　　1.@Target,注解使用范围
　　　　2.@Retention,注解生命周期
　　　　3.@Documented,标记注解  javadoc文档api
　　　　4.@Inherited允许子类继承父类中的注解。
　　这些类型和它们所支持的类在java.lang.annotation包中可以找到。下面我们看一下每个元注解的作用和相应分参数的使用说明。
		
		
		
		
@Target：
		
		　　　@Target说明了Annotation所修饰的对象范围：Annotation可被用于 packages、types（类、接口、枚举、Annotation类型）、类型成员（方法、构造方法、成员变量、枚举值）、方法参数和本地变量（如循环变量、catch参数）。在Annotation类型的声明中使用了target可更加明晰其修饰的目标。
		
		　　作用：用于描述注解的使用范围（即：被描述的注解可以用在什么地方）
		
		　　取值(ElementType)有：
		
		　　　　1.CONSTRUCTOR:用于描述构造器
		　　　　2.FIELD:用于描述域
		　　　　3.LOCAL_VARIABLE:用于描述局部变量
		　　　　4.METHOD:用于描述方法
		　　　　5.PACKAGE:用于描述包
		　　　　6.PARAMETER:用于描述参数
		　　　　7.TYPE:用于描述类、接口(包括注解类型) 或enum声明

@Retention：

		　　@Retention定义了该Annotation被保留的时间长短：
				某些Annotation仅出现在源代码中，而被编译器丢弃；
				而另一些却被编译在class文件中；编译在class文件中的Annotation可能会被虚拟机忽略，
				而另一些在class被装载时将被读取（请注意并不影响class的执行，因为Annotation与class在使用上是被分离的）。
				使用这个meta-Annotation可以对 Annotation的“生命周期”限制。
		
		　　作用：表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效）
		
		　　取值（RetentionPoicy）有：
		
		　　　　1.SOURCE:在源文件中有效（即源文件保留）
		　　　　2.CLASS:在class文件中有效（即class保留）
		　　　　3.RUNTIME:在运行时有效（即运行时保留）
		
		　　Retention meta-annotation类型有唯一的value作为成员，它的取值来自java.lang.annotation.RetentionPolicy的枚举类型值。具体实例如下：

@Documented:

　　@Documented用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化。Documented是一个标记注解，没有成员。
 
@Inherited： 

　　@Inherited 元注解是一个标记注解，@Inherited阐述了某个被标注的类型是被继承的。如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。

　　注意：@Inherited annotation类型是被标注过的class的子类所继承。类并不从它所实现的接口继承annotation，方法并不从它所重载的方法继承annotation。

　　当@Inherited annotation类型标注的annotation的Retention是RetentionPolicy.RUNTIME，则反射API增强了这种继承性。如果我们使用java.lang.reflect去查询一个@Inherited annotation类型的annotation时，反射代码检查将展开工作：检查class和其父类，直到发现指定的annotation类型被发现，或者到达类继承结构的顶层。 

自定义注解：Definition.java类

　　使用@interface自定义注解时，自动继承了java.lang.annotation.Annotation接口，由编译程序自动完成其他细节。在定义注解时，不能继承其他的注解或接口。@interface用来声明一个注解，其中的每一个方法实际上是声明了一个配置参数。方法的名称就是参数的名称，返回值类型就是参数的类型（返回值类型只能是基本类型、Class、String、enum）。可以通过default来声明参数的默认值。


 * 
 *注解Table 可以用于注解类、接口(包括注解类型) 或enum声明,而注解NoDBColumn仅可用于注解类的成员变量。
 */
@Retention(RetentionPolicy.RUNTIME) //表示注解起作用的时段为运行时 
@Target({ElementType.METHOD,ElementType.TYPE})//注解2个参数是用{}逗号分隔
public @interface AnnotationTest {
	String color() default "blue";//设置缺省属相
	String value();//特殊的   使用时如果只有一个value属相可以不写=
	int[] arr() default {1,2,3};//数组类型的注解
	//WeekDay.w1 aa() default WeekDay.w1//枚举类型的注解
	Anno anno() default @Anno("test");//注解类型的属相
}
@interface Anno{
	String value();
}

