package ConfigurationParsing.xml;
/**
 * xm文档定义编写
 * java中解析xml文件有四种方式，分别是DOM、SAX、JDOM、DOM4J   这四种前两种是系统自带的，后两种需要导入jar包，其中先要对xml文件有一个基本的了解。
 * xml文档规范：扩展标识语言，可以实现跨平台，真正做到数据和显示分离（做数据存储方便数据传递）符合文档类型定义（DTD）的规则
 * @author Administrator
 *<?xml version="1.0" encoding="UTF-8"?>
 *		1	第一行定义xml的版本和编码格式   是xml的声明
 *		2<根元素></根元素>有且只有一个根元素
 *		3元素都必须有一个关闭标签   成对关闭或者<节点/>形式关闭   声明不是 XML 文档本身的一部分，它没有关闭标签。
 *		4XML 标签对大小写敏感  标签 <Letter> 与标签 <letter> 是不同的。 <Message>这是错误的</message>
 *		5XML 必须正确嵌套   HTML 中，常会看到没有正确嵌套的元素：
 *		6XML 属性值必须加引号   <note date="12/11/2007"></note>
 *		7在 XML 中，一些字符拥有特殊的意义。  如<   解析会把它当做新标签的开始  &解析器会把该字符解释为字符实体的开始。
 *					在 XML 中，只有字符 "<" 和 "&" 确实是非法的。大于号是合法的，但是用实体引用来代替它是一个好习惯。
 *		对特殊的字符请用实体引用来代替
 *			&lt;	<	小于
			&gt;	>	大于
			&amp;	&	和
			&apos;	'	单引号
			&quot;	"	双引号
 *		8<!-- 这是xml注释语法 -->
 *
 *		9在 XML 中，空格会被保留，HTML 会把多个连续的空格字符裁减（合并）为一个：
 *		10XML 以 LF 存储换行
 *
 *		11  XML 命名规则：名称不能以数字或者标点符号开始，不能包含特殊标点符号，空格,不能以xml,Xml开头
 *						避免使用：，-.特殊符号
 *
 *		12优化：元数据（有关数据的数据）应当存储为属性，而数据本身应当存储为元素。
 *				避免属性利用它可扩展性：属性不能包含多个值（元素可以），不可扩展，不能包含子结构
 *	
 *		13XML 文档中的错误会终止您的 XML 应用程序  可以通过验证工具DTD规范验证
 *：
 *		14浏览器可以对xml文档按树结构的展示
 *
 *		15使用 CSS 来格式化 XML 文档是有可能的。在xml中引入<?xml-stylesheet type="text/css" href="cd_catalog.css"?>
 *
 *		16.XSLT 是首选的 XML 样式表语言 XSLT 是在浏览器显示 XML 文件之前，先把它转换为 HTML：当浏览器读取 XML 文件时，XSLT 转换是由浏览器完成的。
 *
 *		17命名冲突   可以通过前缀 xx:命名的形式区分
 *				<table><tr><td>Apples</td><td>Bananas</td></tr></table>
 *				<table><name>African Coffee Table</name><width>80</width><length>120</length></table>
 *			改为	<h:table><h:tr><h:td>Apples</h:td><h:td>Bananas</h:td></h:tr></h:table>
 *				<f:table><f:name>African Coffee Table</f:name><f:width>80</f:width><f:length>120</f:length></f:table>
 *
 *		18 XML 命名空间 - xmlns 属性
 *			当在 XML 中使用前缀时，一个所谓的用于前缀的命名空间必须被定义。
 *			命名空间是在元素的开始标签的 xmlns 属性中定义的。xmlns:前缀="URI"
 *			也可以在他们被使用的元素中或者在 XML 根元素中声明：
 *			<h:table xmlns:h="http://www.w3.org/TR/html4/">
 *				<h:tr><h:td>Apples</h:td><h:td>Bananas</h:td></h:tr>
 *			</h:table>
 *
 *
 *		19 CDATA 部分由 "<![CDATA[" 开始，由 "]]>" 结束：不应该由 XML 解析器解析的文本数据。
 *				不能包含字符串 "]]>"。也不允许嵌套的 CDATA 部分。
 *				标记 CDATA 部分结尾的 "]]>" 不能包含空格或换行。
 *描述规范 DOM 实质上是一些节点的集合（Document、Element、Text、Attr 、CDATASection、
 *
 *
 *							ProcessingInstruction、Notation 、
 *							EntityReference、Entity、DocumentType、DocumentFragment）
 *DOM（Document Object Model 文档对象模型）定义了访问和操作文档的标准方法。
 */
public class MyXmlFile {
	public static void main(String[] args) {
		
	}
	/**
	 * 浏览器端解析xml
	 * 所有现代浏览器都有内建的 XML 解析器。文档转换为 XML DOM 对象 - 可通过 JavaScript 操作的对象。
	 */
	public void jsFunction() {
		/*XMLHttpRequest xmlhttp=null;
		if (window.XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}else{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.open("GET","books.xml",false);
		xmlhttp.send();
		var xmlDoc=xmlhttp.responseXML;//解析 XML 文档
		*/
		//解析xml字符窜
		/*var txt="<bookstore><book>";
		txt=txt+"<title>Everyday Italian</title>";
		txt=txt+"<author>Giada De Laurentiis</author>";
		txt=txt+"<year>2005</year>";
		txt=txt+"</book></bookstore>";

		if (window.DOMParser){
			var parser=new DOMParser();
			var xmlDoc=parser.parseFromString(txt,"text/xml");
		}else{ // Internet Explorer
		
			var xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
			xmlDoc.async=false;
			xmlDoc.loadXML(txt); 
		}*/
	}
}
