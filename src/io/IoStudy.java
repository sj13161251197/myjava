package io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;

/**
 * 数据源和目标媒介：文件，管道 ，网络链接，内存缓存，System.in, System.out, System.error
 * 
 * 具体有按读写分：输入，输出流
 * 按流处理类型：字节，字符
 * 
 * 具体：文件访问，网络访问，内存缓存访问，线程内部通信(管道)，缓冲，过滤，解析，
 * 		
 * 		读写文本 (Readers / Writers)，读写基本类型数据 (long, int etc.)，读写对象
 * 应用实例api
 * writeByteToFile--用字节流写文件
 * readByteFromFile--用字节读写文件
 * writeCharToFile--字符流写文件
 * readCharFromFile
 * convertByteToChar 字节流转为字符流
 * FileDemo
 * randomAccessFileRead随机读取文件
 * PipeExample管道流
 * readByBufferedInputStream缓冲流
 * BufferedReader
 */

public class IoStudy {
	public static void main(String[] args) throws IOException{
		//readByBufferedInputStream();  
		inToOut2();
	}
	/**
	 * 有东西的叫输入流-进入内存叫做输入流，从内存出来叫输出流
	 */
	static void inToOut(){
		 FileInputStream fis = null;
	        FileOutputStream fos = null;
	        								
	        try {
	            fis = new FileInputStream("D:\\staticresource\\upload\\report\\pdf\\20190428\\201904281721_475.pdf");
	            fos = new FileOutputStream("D:\\staticresource\\upload\\report\\pdf\\20190428\\1.pdf");
	            byte[] buf = new byte[1024];
	            int len = 0;
	            while((len=fis.read(buf))>=0){
	                fos.write(buf, 0, len);
	                //System.out.write(buf, 0, len);//这是往控制台输出；
	            }
	            
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally{
	            try {
	                if(fis!=null) fis.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            try {
	                if(fos!=null) fos.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	}
	/**
	 * 没东西的叫输入流
	 */
	static void inToOut2(){
		 FileInputStream fis = null;
	        FileOutputStream fos = null;
	        								
	        try {
	            fis = new FileInputStream("D:\\staticresource\\upload\\report\\pdf\\20190428\\1.pdf");
	            fos = new FileOutputStream("D:\\staticresource\\upload\\report\\pdf\\20190428\\201904281721_475.pdf");
	            byte[] buf = new byte[1024];
	            int len = 0;
	            while((len=fis.read(buf))>=0){
	                fos.write(buf, 0, len);
	                //System.out.write(buf, 0, len);//这是往控制台输出；
	            }
	            
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally{
	            try {
	                if(fis!=null) fis.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            try {
	                if(fos!=null) fos.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	}
	/**
	 * 用字节流写文件
	 * @throws IOException
	 */
	 public static void writeByteToFile() throws IOException{
	        String hello= new String( "hello word!汉字");
	         byte[] byteArray= hello.getBytes();
	        File file= new File( "d:/test.txt");
	         //因为是用字节流来写媒介，所以对应的是OutputStream 
	         //又因为媒介对象是文件，所以用到子类是FileOutputStream
	        OutputStream os= new FileOutputStream( file);
	         os.write( byteArray);
	         os.close();
	  }
	 /**
	  * 用字节流读文件
	  * @throws IOException
	  */
	 public static void readByteFromFile() throws IOException{
	        File file= new File( "d:/test.txt");
	         byte[] byteArray= new byte[( int) file.length()];
	         //因为是用字节流来读媒介，所以对应的是InputStream
	         //又因为媒介对象是文件，所以用到子类是FileInputStream
	        InputStream is= new FileInputStream( file);
	         int size= is.read( byteArray);
	        System. out.println( "大小:"+size +";内容:" +new String(byteArray));
	         is.close();
	  }
	 /**
	  * 字符流写文件
	  * @throws IOException
	  */
	 public static void writeCharToFile() throws IOException{
	        String hello= new String( "hello word汉字a!");
	        File file= new File( "d:/test.txt");
	         //因为是用字符流来读媒介，所以对应的是Writer，又因为媒介对象是文件，所以用到子类是FileWriter
	        Writer os= new FileWriter( file);
	         os.write( hello);
	         os.close();
	  }
	 /**
	  * 字符流读文件
	  * @throws IOException
	  */
	 public static void readCharFromFile() throws IOException{
	        File file= new File( "d:/test.txt");
	         //因为是用字符流来读媒介，所以对应的是Reader
	         //又因为媒介对象是文件，所以用到子类是FileReader
	        Reader reader= new FileReader( file);
	         char [] byteArray= new char[( int) file.length()];
	         int size= reader.read( byteArray);
	        System. out.println( "大小:"+size +";内容:" +new String(byteArray));
	         reader.close();
	  }
	 /**
	  * 字节流转字符流
	  * @throws IOException
	  */
	 public static void convertByteToChar() throws IOException{
	        File file= new File( "d:/test.txt");
	         //获得一个字节流
	        InputStream is= new FileInputStream( file);
	         //把字节流转换为字符流，其实就是把字符流和字节流组合的结果。
	        Reader reader= new InputStreamReader( is);
	         char [] byteArray= new char[( int) file.length()];
	         int size= reader.read( byteArray);
	        System. out.println( "大小:"+size +";内容1:" +new String(byteArray));
	         is.close();
	         reader.close();
	  }
	 public static void FileDemo (){
		 //检查文件是否存在
	        File file = new File( "d:/test.txt");
	         boolean fileExists = file.exists();
	        System. out.println( fileExists);
	         //创建文件目录,若父目录不存在则返回false
	        File file2 = new File( "d:/fatherDir/subDir");
	         boolean dirCreated = file2.mkdir();
	        System. out.println( dirCreated);
	         //创建文件目录,若父目录不存则连同父目录一起创建
	        File file3 = new File( "d:/fatherDir/subDir2");
	         boolean dirCreated2 = file3.mkdirs();
	        System. out.println( dirCreated2);
	        File file4= new File( "d:/test.txt");
	         //判断长度
	         long length = file4.length();
	         //重命名文件
	         boolean isRenamed = file4.renameTo( new File("d:/test2.txt"));
	         //删除文件
	         boolean isDeleted = file4.delete();
	        File file5= new File( "d:/fatherDir/subDir");
	         //是否是目录
	         boolean isDirectory = file5.isDirectory();
	         //列出文件名
	        String[] fileNames = file5.list();
	         //列出目录
	        File[]   files = file4.listFiles();
	 }
	 /**
	  * 时候我们只希望读取文件的一部分，或者是说随机的读取文件
	  * RandomAccessFile提供了seek()方法，用来定位将要读写文件的指针位置
	  * getFilePointer()方法来获取当前指针的位置
	  * @throws IOException
	  */
	 public static void randomAccessFileRead() throws IOException {
         // 创建一个RandomAccessFile对象
		 //第二个参数：mode参数指定的文件被打开的访问模式-即读写的权限能力。允许的值和它们的意思是：

        RandomAccessFile file = new RandomAccessFile( "d:/test.txt", "rw");//r只读..rw读和写..
         // 通过seek方法来移动读写位置的指针
         file.seek(10);
         // 获取当前指针
         long pointerBegin = file.getFilePointer();
         // 从当前指针开始读
         byte[] contents = new byte[1024];
         file.read( contents);
         long pointerEnd = file.getFilePointer();
        System. out.println( "pointerBegin:" + pointerBegin + "\n" + "pointerEnd:" + pointerEnd + "\n" + new String(contents));
         file.close();
  }
	 /**
	  * 随机写入文件
	  * @throws IOException
	  */
	 public static void randomAccessFileWrite() throws IOException {
         // 创建一个RandomAccessFile对象
        RandomAccessFile file = new RandomAccessFile( "d:/test.txt", "rw");
         // 通过seek方法来移动读写位置的指针
         file.seek(10);
         // 获取当前指针
         long pointerBegin = file.getFilePointer();
         // 从当前指针位置开始写
         file.write( "HELLO WORD".getBytes());
         long pointerEnd = file.getFilePointer();
        System. out.println( "pointerBegin:" + pointerBegin + "\n" + "pointerEnd:" + pointerEnd + "\n" );
         file.close();
  }
	 /**
	  * 管道主要用来实现同一个虚拟机中的两个线程进行交流
	  * 
	  * @throws IOException
	  */
	 public static void PipeExample () throws IOException{
		 final PipedOutputStream output = new PipedOutputStream();
         final PipedInputStream  input  = new PipedInputStream(output);
         Thread thread1 = new Thread( new Runnable() {
             @Override
             public void run() {
                 try {
                     output.write( "Hello world,pipe!".getBytes());
                 } catch (IOException e) {
                 }finally {
                	 try {
						output.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
             }
         });
         Thread thread2 = new Thread( new Runnable() {
             @Override
             public void run() {
                 try {
                     int data = input.read();
                     while( data != -1){
                         System. out.print(( char) data);
                         data = input.read();
                     }
                 } catch (IOException e) {
                	 e.printStackTrace();
                 } finally{
	                   try {
	                          input.close();
	                   } catch (IOException e) {
	                          e.printStackTrace();
	                   }
                 }
             }
         });
         thread1.start();
         thread2.start();
	 }
	 /**
	  * 对流进行写入时提供一个buffer来提高IO效率。
	  * 在进行磁盘或网络IO时，原始的InputStream对数据读取的过程都是一个字节一个字节操作的，
	  * 而BufferedInputStream在其内部提供了一个buffer，
	  * 在读数据时，会一次读取一大块数据到buffer中，这样比单字节的操作效率要高的多，
	  * 特别是进程磁盘IO和对大量数据进行读写的时候。
	  * 
	  * 
	  * 如何设置buffer的大小，我们应根据我们的硬件状况来确定。
	  * 对于磁盘IO来说，如果硬盘每次读取4KB大小的文件块，那么我们最好设置成这个大小的整数倍。
	  * 因为磁盘对于顺序读的效率是特别高的，
	  * 所以如果buffer再设置的大写可能会带来更好的效率，比如设置成4*4KB或8*4KB。
	  * 
	  * 磁盘本身就会有缓存，这种情况下，BufferedInputStream会一次读取磁盘缓存大小的数据
	  * @throws IOException
	  */
	 public static void readByBufferedInputStream() throws IOException {
	        File file = new File( "d:/test.txt");
	         byte[] byteArray = new byte[( int) file.length()];
	         //可以在构造参数中传入buffer大小
	        InputStream is = new BufferedInputStream( new FileInputStream(file),2*1024);
	         int size = is.read( byteArray);
	        System. out.println( "大小:" + size + ";内容:" + new String(byteArray));
	         is.close();
	  }
	 public static void readByBufferedReader() throws IOException {
	        File file = new File( "d:/test.txt");
	         // 在字符流基础上用buffer流包装，也可以指定buffer的大小
	        Reader reader = new BufferedReader( new FileReader(file),2*1024);
	         char[] byteArray = new char[( int) file.length()];
	         int size = reader.read( byteArray);
	        System. out.println( "大小:" + size + ";内容:" + new String(byteArray));
	         reader.close();
	  }
}
