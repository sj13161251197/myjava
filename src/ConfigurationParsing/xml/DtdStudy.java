package ConfigurationParsing.xml;
/**
 * https://blog.csdn.net/gavin_john/article/details/51532756
 * 是对xml进行约束的规范文档
 * 在xml中引入规范
 * 		<!DOCTYPE 班级 SYSTEM "myClass.dtd">
 * 		来源：SYSTEM  本地，PUBLIC网络
 * DTD文档的声明及引用   myClass.dtd
 * 		内部DTD文档   <!DOCTYPE 根元素 [定义内容]>
 * 		外部DTD文档
 * 			<!DOCTYPE 根元素 SYSTEM "DTD文件路径">    DTD文件是本地文件的时候
 * 			<!DOCTYPE 根元素 PUBLIC "DTD名称" "DTD文件的URL">
 * 				<!DOCTYPE web-app PUBLIC "-//Sun Microsystems,Inc.//DTD Web Application 2.3//EN" "http://java.sun.com/dtd/web-app_2_3.dtd">

 *DTD基本语法
 *		<!ELEMENT NAME CONTENT>
 *			CONTENT是元素类型，必须要大写！CONTENT的内容有三种写法：
 *			(1)EMPTY——表示该元素不能包含子元素和文本，但可以有属性。 
			(2)ANY——表示该元素可以包含任何在该DTD中定义的元素内容 
			(3)#PCDATA——可以包含任何字符数据，但是不能在其中包含任何子元素
		组合
		<!ELEMENT 家庭(人+,家电*)>
			家庭元素中可以有1到多个”人”这个子元素，也可以有0到多个”家电”这个子元素
			()	用来给元素分组	(古龙|金庸),(王朔|余杰)	分成两组
			|	在列出的对象中选择一个	(男人|女人)	表示男人或者女人必须出现，两者至少选其一
			+	该对象必须出现一次或者多次	(成员+)	表示成员必须出现，而却可以出现多个成员
			*	该对象允许出现0次或者多次	(爱好*)	爱好可以出现两次到多次
			?	该对象必须出现0次或者1次	(菜鸟?)	菜鸟可以出现，也可以不出现，如果出现的话，最多只能出现一次
			,	对象必须按指定的顺序出现	(西瓜,苹果,香蕉)	表示西瓜、苹果、香蕉必须出现，并且按这个顺序出现
			
		属性定义语法	
		<!ATTLIST 元素名称
		 	属性名称 类型 属性特点
		 	属性名称 类型 属性特点......  
		>	
		属性类型
			(1) CDATA 表示属性值可以是任何字符（包括中文和数字）
			(2) ID 表明该属性的取值必须是唯一的，但是属性的值不能是以数字开头
			(3) IDREF/IDREFS 值指向文档中其它地方声明的ID类型的值 
			(4) Enumerated 事先定义好一些值，属性的值必须在所列出的值的范围内
					<!ATTLIST person 婚姻状态 (single|married|divorced|widowed) #IMPLIED>
			(5) ENTITY/ENTITIES 实体用于为一段内容创建一个别名，以后在XML文档中就可以使用别名引用这段内容了
					应用实体：<!ENTITY 实体名称 "实体内容">
					参数实体：<!ENTITY % 实体名称 "实体内容">    如<!ENTITY % TAG_NAME "姓名|EMAIL|电话|地址">
		属性特点
			(1) #REQUIRED，表示这个属性必须给，不给就报错 
			(2) #IMPLIED，表示这个属性可以给也可以不给 
			(3) #FIXED value，表示这个属性必须给一个固定的value值 
			(4) Default value，表示这个属性如果没有值，就分配一个默认的value值
 */
public class DtdStudy {
	public static void main(String[] args) {
		
	}
}
