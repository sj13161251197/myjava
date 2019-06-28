package Pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {
	public static void main(String[] args) {
		//方式一：
		//将一个字符串编译成Pattern对象
		Pattern p = Pattern.compile("正则表达式");
		//使用Pattern对象创建Matcher对象
		Matcher m = p.matcher("要匹配的字符串");
		m.matches() ; //返回true 表示匹配 返回false表示不匹配
		
		//方式二 ： 使用Pattern下的静态方法 matches
		Pattern.matches("正则","字符串");
		//方式三：
		//String类下提供了直接匹配的方法
		String str="";
		str.matches("正则") ;

	}
	/**
	 * 正则表达式是一个字符串模板，可以表示一批字符串，所以创建正则表达式就是创建一个特殊的字符串
正则表达式中的特殊字符
.    表示任意一个字符
[]   表示括号中表达式开始和结束的位置
{}   表示表达式出现的频度
()    表示子表达式开始和结束的位置
*      表示零次或多次
+     表示一次或多次
？    表示零次或一次
^     表示开头
$     表示结尾
|      表示或者，两项中任选一项
\      转意
特殊字符
\d  表示 0-9
\D  表示非数字
\s   表示所有空白字符  空格 tab 回车等
\S   表示非空白字符
\w   表示所有单词字符 包括0-9 26因为字母和_
\W  表示非单词字符
方括号的使用
[abc]表示 a,b,c中任选一个
[a-f]   表示a到f
[^abc] 表示非a,b,c  和^[abc]完全不同 
[a-z&&[def]] 表示a-z和def的交集 即 def
思考[a-z&&[^bc]]表示什么？
[a-d[x-z]] 等同于[a-dx-z]
{}的使用  表示频度

x{n}  表示一定要出现n次
x{n,m} 出现n到m次
x{n,} 至少出现n次
x?
x*
x+
\b 表示单词边界
例如："hi.*"   和  "high" 匹配
      "ahi.*" 和  "ahigh" 匹配
     "a.hi.*"和  "axhigh" 匹配
但 "a.\\bhi.*" 和  "axhigh" 不匹配  
    "a.\\bhi.*" 和  "a high" 就匹配  为什么？
因为\b表示单词的边界  空格把 a 和high分成了两个单词 也就是说 high是另一个单词的开始 处在边界上  所以就匹配  而x不会把a和high分成两个单词
思考 "a.\\bhi.*" 和  "a,high" 是否匹配？
\B   表示非单词边界

	 */
}
