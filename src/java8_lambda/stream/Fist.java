package java8_lambda.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
/**
 * 4.1 Stream概述
 * Stream API是Lambda表达式的具体应用
 * IO流中的Stream可以理解为字节序列或者字符序列，
 * StAX中的Stream可以理解为XML文档元素的序列，
 * 而这里的Stream指的是一组元素的序列，这一组元素可以来源于数组，集合或者是IO channel。
 * 
 * Stream API是原先集合Iterator的高级版本
 * 	常规的串行式集合迭代与处理外，也提供了并行式的集合迭代与处理
 * 
 * stream()方法外
 * 	串行式处理就是在一个线程内将元素从头到尾依次迭代处理
 * parallelStream()方法
 * 	并行式处理是指将一组元素分割成多个数据段，每个数据段单独启用一个线程，
 * 		各线程彼此独立同时运行，从而充分利用现代处理器多核多线程的特点，提高系统性能。
 * 	
 * @author Administrator
 *
 */
public class Fist {
	public static void main(String[] args) {
		test1();
		test3();
	}
	/**
	 * stream概述
	 * stream被执行遍历之后不可以在被调用
	 */
	public static void test1(){
		Stream<Integer> stream=Stream.of(1,2,3,4,5);
		//stream.forEach((s)->{System.out.println(s);});
		stream.forEach(s->System.out.println(s));
		//注意必须添加limit()方法限制产生的元素的个数，否则会产生无穷序列
		Stream<Integer> stream2=Stream.generate(()->{
			return new Random().nextInt(5);
		}).limit(5);
		stream2.forEach((s)->{System.out.println(s);});
		
		//iterate方法中，第一个参数是种子，即最终产生的序列中的第一个元素，第二个元素是由后面的
		//Lambda表达式计算而来的，这里是由前一个元素加1产生后一个元素
		Stream<Integer> stream3=Stream.iterate(1,item->item+1).limit(5);
		stream3.forEach((s)->{System.out.println(s);});
		
		int[] a=new int[]{1,2,3,4,5};
		//IntStream是Stream针对int或者Interger元素类型的特定Stream类型，跟Stream处在相同
		//的类级别位置，都是BaseStream的子类。同样的还有LongStream和DoubleStream。
		IntStream stream4=Arrays.stream(a);
		stream4.forEach((s)->{System.out.println(s);});
		
		IntStream stream5=IntStream.range(1, 5);
		stream5.forEach((s)->{System.out.println(s);});
		
		List<Integer> b=Arrays.asList(1,2,3,4,5);
		Stream<Integer> stream6=b.stream();
		stream6.forEach((s)->{System.out.println(s);});
		
		try {
			File file = new File("sample.js");
			System.out.println(file.getAbsolutePath());
			BufferedReader reader=new BufferedReader(new FileReader(file));
		    //返回的序列中Stream代表文本中一行字符串
			Stream<String> stream7=reader.lines();
			stream7.forEach(s->{
				System.out.println(s);
				}
			);
		    //stream7.forEach(s->System.out.println(s));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 *    Stream操作分为两类：

		Intermediate：一个流可以后面跟随零个或多个 intermediate 操作。
			其目的主要是打开流，做出某种程度的数据映射/过滤，然后返回一个新的流，交给下一个操作使用。
			这类操作都是惰性化的（lazy），
			就是说，仅仅调用到这类方法，并没有真正开始流的遍历。
		Terminal：一个流只能有一个 terminal 操作，当这个操作执行后，流就被使用“光”了，无法再被操作。
			所以这必定是流的最后一个操作。
			Terminal 操作的执行，才会真正开始流的遍历，并且会生成一个结果，或者一个 side effect。
			注意由Intermediate操作返回的新的流被关闭了，实际未发生改变的原始流同样被关闭了，无法再被操作。
         	需要注意的是对流执行的多个操作的过程中，流中的元素只遍历一遍，
			可以将流的各种操作理解一种筛子，每个元素会按照Stream中的序列依次通过各个筛子，
			如果某元素被某个筛子过滤掉了，那么该元素就不会执行该筛子后面的筛子操作了。


	 */
	//Intermediate操作，该类操作的关键特点是返回一个新的流
	public static void test3(){
		Stream<Integer> stream=Stream.of(1,2,2,3,3,3,4,7,8,11,5,6,10);
		//去除重复
		stream.distinct().forEach(s->System.out.print(s+"|"));
		System.err.println("**********************去除重复***********************");
		//过滤大于4的包含给过滤掉
		Stream<Integer> stream2=Stream.of(1,2,2,3,3,3,4,7,8,11,5,6,10);
        stream2.filter(s->s>4).forEach(s->System.out.print(s+"|"));
    	System.err.println("*******************过滤大于4的元素**********************");
        //映射,三个重载的方法mapToInt,mapToDouble,mapToLong
        Stream<Integer> stream3=Stream.of(1,2,2,3,3,3,4,7,8,11,5,6,10);
       
        stream3.map(s->s*2).forEach(m->System.out.print(m+"|"));
        System.err.println("********************映射为新的对象**********************");
        //映射，与map不同的是flatMap返回的是流对象，即将原始流中的元素转换为流对象
        Integer[][] a=new Integer[][]{{1,2,3},{4,5,6},{7,8,9}};
        Stream<Integer[]> stream4=Arrays.stream(a);
        //stream4.flatMap(s->Stream.of(s)).forEach(System.out::print);
        stream4.flatMap(s->Stream.of(s)).forEach(m->System.out.print(m+"|"));
        System.err.println("*********************映射为流对象***********************");
        
        
        //peek方法等价于forEach方法，不过设计该方法的初衷是将该方法用于调试打印流中的元素
        Stream<Integer> stream5=Stream.of(1,2,2,3,3,3,4,7,8,11,5,6,10);
        stream5.peek(System.out::print).forEach(System.out::print);
        System.err.println("*********************************************");
	    //skip方法用于抛弃流中指定个数的元素，返回一个新的流，如果流中的元素个数小于指定个数则返回空的流，该方法对于操作序列化的流
        //来说是廉价的操作，对于并行处理的流则是代价较高的操作
        Stream<Integer> stream6=Stream.of(1,2,2,3,3,3,4,7,8,11,5,6,10);
        stream6.skip(5).forEach(System.out::print);
        System.err.println("*********************************************");
        //distinct方法用于去除重复的元素
        Stream<Integer> stream7=Stream.of(1,2,2,3,3,3,4,7,8,11,5,6,10);
        stream7.distinct().forEach(System.out::print);
        System.err.println("*********************************************");
        Consumer<Integer> print=System.out::print;
        //sorted方法返回一个按照自然顺序排序的流,与之相反的是unordered方法
        Stream<Integer> stream8=Stream.of(1,2,2,3,3,3,4,7,8,11,5,6,10);
        stream8.sorted().forEach(print);
        
	}
	//Terminal操作，该类操作的关键特点是操作执行完后，原始流和经过Intermediate操作产生的新的流都会被关闭
	public static void test4(){
		Stream<Integer> stream=Stream.of(1,2,2,3,3,3,4,7,8,11,5,6,10);
		
		//toArray方法返回一个Object数组，注意不能强转
		Object[] result =stream.toArray();
		
		//判断流中所有的元素是否都满足某个条件，类似的有anyMatch、noneMatch方法
		Stream<Integer> stream2=Stream.of(1,2,2,3,3,3,4,7,8,11,5,6,10);
		boolean match=stream2.allMatch(s->s>5);
		
		//从流中选取任意的一个元素到Optional容器中，get方法用于迭代容器中的元素，ispresent判断该元素是否为空
		//ifpresent方法表示当容器元素非空时应用某个Consumer,注意多次操作同一个数据源结果可能不一样
		//如果想要一个确定的结果用findFirst方法
		Stream<Integer> stream3=Stream.of(1,2,2,3,3,3,4,7,8,11,5,6,10);
		Optional<Integer> optional=stream3.findAny();
	    System.out.println(optional.get());
	    
	    //iterator方法返回一个迭代器对象
	    Stream<Integer> stream4=Stream.of(1,2,2,3,3,3,4,7,8,11,5,6,10);
	    Iterator<Integer> iterator=stream4.iterator();
	    while(iterator.hasNext()){
	    	System.out.println(iterator.next());
	    }
	    
	    //min取出流中最小的元素,用Optional容器包装，相反的是max方法
	    Stream<Integer> stream5=Stream.of(1,2,2,3,3,3,4,7,8,11,5,6,10);
	    Optional<Integer> min=stream5.min((s,s1)->s-s1);

	    //count方法返回元素总个数
	    Stream<Integer> stream6=Stream.of(1,2,2,3,3,3,4,7,8,11,5,6,10);
	    System.out.println(stream6.count());
	    
	    //collect方法，主要利用Collectors对象中的静态方法，这些静态方法能够实现常用的分组，求和，求平均值等统计分析功能
	    //将流中的元素转换为list列表，类似的有toMap，toSet,toCollection方法
	    Stream<Integer> stream7=Stream.of(1,2,2,3,3,3,4,7,8,11,5,6,10);
	    List<Integer> list=stream7.collect(Collectors.toList());
	    list.forEach(System.out::print);
	    
	    //转换为map时注意去除重复元素，避免key值重复
	    Stream<Integer> stream8=Stream.of(1,2,2,3,3,3,4,7,8,11,5,6,10);
	    Map<Integer, Integer> map=stream8.distinct().collect(Collectors.toMap(s->s, s->s*s));
	    map.forEach((a,b)->System.out.println(a+":"+b));

	    //将流中的元素用自定的字符连接起来，重载方法可以加前缀后缀
	    Stream<Integer> stream9=Stream.of(1,2,2,3,3,3,4,7,8,11,5,6,10);
	    String num=stream9.filter(s->s>5).map(s->s.toString()).collect(Collectors.joining(", "));
	   System.out.println(num);
	    
	   //求总和
	    Stream<Integer> stream10=Stream.of(1,2,2,3,3,3,4,7,8,11,5,6,10);
	    int sum=stream10.collect(Collectors.summingInt(s->s));
	    System.out.println(sum);
	    
	    //求平均值，返回的Double对象
	    Stream<Integer> stream11=Stream.of(1,2,2,3,3,3,4,7,8,11,5,6,10);
	    int average=stream11.collect(Collectors.averagingInt(s->s)).intValue();
	    System.out.println(average);
	    
	    //reduce方法，其中0相当于计算的初始值，若stream为空则直接返回0，后面的lambda表达式是执行实际计算的规则，具体而言以起始值和流中的第一个元素
	    //为参数调用后面的规则，将返回值作为第一个参数，和流中的第二个元素一起作为参数继续调用规则，直到流中的元素没有，返回最后一次调用规则的返回值。
	    Stream<Integer> stream13=Stream.of(1,2,2,3,3,3,4,7,8,11,5,6,10);
	    int sum2=stream13.reduce(0, (sum3,item)->sum3+item);
	    System.out.println(sum2);
	    
	 // 字符串连接，concat = "ABCD"
	    String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat); 
	 // 求最小值，minValue = -3.0
	    double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min); 
	}

}
