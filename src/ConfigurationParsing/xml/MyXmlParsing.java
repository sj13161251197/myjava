package ConfigurationParsing.xml;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.jdom.*;

import org.jdom.input.*;



/**
 * xml文档的解析
 * https://www.cnblogs.com/longqingyang/p/5577937.html
 * 1、DOM解析；2、SAX解析；3、JDOM解析；4、DOM4J解析。其中前两种属于基础方法，是官方提供的平台无关的解析方式；后两种属于扩展方法，它们是在基础的方法上扩展出来的，只适用于java平台。
 * DOM4J性能最好
 * 		它合并了许多超出基本XML文档表示的功能，包括集成的XPath支持、XML Schema支持以及用于大文档或流化文档的基于事件的处理。它还提供了构建文档表示的选项
 * SAX表现较好，这要依赖于它特定的解析方式－事件驱动。一个SAX检测即将到来的XML流，但并没有载入到内存（当然当XML流被读入时，会有部分文档暂时隐藏在内存中）。
 * @author Administrator
 *
 */
public class MyXmlParsing {
	public static void main(String[] args) throws org.xml.sax.SAXException {
		MyXmlParsing myXmlParsing = new MyXmlParsing();
		//myXmlParsing.domParsing();//dom解析
		//myXmlParsing.saxParsing();//SAX解析
		myXmlParsing.jdomParsing();//
	}
	/**
	 * DOM解析
	 *  基于DOM的XML分析器将一个XML文档转换成一个对象模型的集合（通常称DOM树）
	 *  优点：

　　　　　　1、形成了树结构，有助于更好的理解、掌握，且代码容易编写。

　　　　　　2、解析过程中，树结构保存在内存中，方便修改。

　　　　缺点：

　　　　　　1、由于文件是一次性读取，所以对内存的耗费比较大。

　　　　　　2、如果XML文件比较大，容易影响解析性能且可能会造成内存溢出。
	 * @throws org.xml.sax.SAXException 
	 */
	public void domParsing() throws org.xml.sax.SAXException {
		//创建一个DocumentBuilderFactory的对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //创建一个DocumentBuilder的对象
        try {
            //创建DocumentBuilder对象
            DocumentBuilder db = dbf.newDocumentBuilder();
            //通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下
            Document document = db.parse("src/ConfigurationParsing/xml/book.xml");
            //Document doc = db.parse(new File("data_10k.xml"));
            //获取所有book节点的集合
            NodeList bookList = document.getElementsByTagName("book");
            //通过nodelist的getLength()方法可以获取bookList的长度
            System.out.println("一共有" + bookList.getLength() + "本书");
            //遍历每一个book节点
            for (int i = 0; i < bookList.getLength(); i++) {
                System.out.println("=================下面开始遍历第" + (i + 1) + "本书的内容=================");
                //通过 item(i)方法 获取一个book节点，nodelist的索引值从0开始
                Node book = bookList.item(i);
                //获取book节点的所有属性集合
                NamedNodeMap attrs = book.getAttributes();
                System.out.println("第 " + (i + 1) + "本书共有" + attrs.getLength() + "个属性");
                //遍历book的属性
                for (int j = 0; j < attrs.getLength(); j++) {
                    //通过item(index)方法获取book节点的某一个属性
                    Node attr = attrs.item(j);
                    //获取属性名
                    System.out.print("属性名：" + attr.getNodeName());
                    //获取属性值
                    System.out.println("--属性值" + attr.getNodeValue());
                }
                //解析book节点的子节点
                NodeList childNodes = book.getChildNodes();
                //遍历childNodes获取每个节点的节点名和节点值
                System.out.println("第" + (i+1) + "本书共有" + childNodes.getLength() + "个子节点");
                for (int k = 0; k < childNodes.getLength(); k++) {
                    //区分出text类型的node以及element类型的node
                    if (childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
                        //获取了element类型节点的节点名
                        System.out.print("第" + (k + 1) + "个节点的节点名：" 
                        + childNodes.item(k).getNodeName());
                        //获取了element类型节点的节点值
                        System.out.println("--节点值是：" + childNodes.item(k).getFirstChild().getNodeValue());
                        //System.out.println("--节点值是：" + childNodes.item(k).getTextContent());
                    }
                }
                System.out.println("======================结束遍历第" + (i + 1) + "本书的内容=================");
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }      
        
	}
	/**
	 * SAX解析
	 * SAX提供的访问模式是一种顺序模式，这是一种快速读写XML数据的方式。
	 * 	当使用SAX分析器对XML文档进行分析时，会触发一系列事件，并激活相应的事件处理函数，应用程序通过这些事件处理函数实现对XML文档的访问，因而SAX接口也被称作事件驱动接口。
	 * 优点：

　　　　　　1、采用事件驱动模式，对内存耗费比较小。

　　　　　　2、适用于只处理XML文件中的数据时。

　　　　缺点：

　　　　　　1、编码比较麻烦。

　　　　　　2、很难同时访问XML文件中的多处不同数据。
	 * @throws org.xml.sax.SAXException 
	 */
	public void saxParsing() throws org.xml.sax.SAXException {
		//创建解析工程
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //获取解析器
        try {
           SAXParser parser = factory.newSAXParser();
            //创建解析分析驱动
            SAXParserHandler handler = new SAXParserHandler();
            parser.parse("src/ConfigurationParsing/xml/book.xml", handler);
            System.out.println("~！~！~！共有" + handler.getBookList().size() + "本书");
            for (Book book : handler.getBookList()) {
                System.out.println(book.getId());
                System.out.println(book.getName());
                System.out.println(book.getAuthor());
                System.out.println(book.getYear());
                System.out.println(book.getPrice());
                System.out.println(book.getLanguage());
                System.out.println("----finish----");
            }
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	/**
	 * JDOM解析
	 *  它简化与XML的交互并且比使用DOM实现更快
	 * JDOM仅使用具体类而不使用接口
	 */
	public void jdomParsing() {
		ArrayList<Book> booksList = new ArrayList<Book>();
		// 进行对books.xml文件的JDOM解析
        // 准备工作
        // 1.创建一个SAXBuilder的对象
        SAXBuilder saxBuilder = new SAXBuilder();
        InputStream in;
        try {
            // 2.创建一个输入流，将xml文件加载到输入流中
            in = new FileInputStream("src/ConfigurationParsing/xml/book.xml");
            InputStreamReader isr = new InputStreamReader(in, "UTF-8");
            // 3.通过saxBuilder的build方法，将输入流加载到saxBuilder中
            org.jdom.Document document = saxBuilder.build(isr);
           
			// 4.通过document对象获取xml文件的根节点
            Element rootElement = document.getRootElement();
            // 5.获取根节点下的子节点的List集合
            List<Element> bookList = rootElement.getChildren();
            // 继续进行解析
            for (Element book : bookList) {
                Book bookEntity = new Book();
                System.out.println("======开始解析第" + (bookList.indexOf(book) + 1) + "书======");
                // 解析book的属性集合
                List<Attribute> attrList = book.getAttributes();
                // //知道节点下属性名称时，获取节点值
                // book.getAttributeValue("id");
                // 遍历attrList(针对不清楚book节点下属性的名字及数量)
                for (Attribute attr : attrList) {
                    // 获取属性名
                    String attrName = attr.getName();
                    // 获取属性值
                    String attrValue = attr.getValue();
                    System.out.println("属性名：" + attrName + "----属性值：" + attrValue);
                    if (attrName.equals("id")) {
                        bookEntity.setId(attrValue);
                    }
                }
                // 对book节点的子节点的节点名以及节点值的遍历
                List<Element> bookChilds = book.getChildren();
                for (Element child : bookChilds) {
                    System.out.println("节点名：" + child.getName() + "----节点值：" + child.getValue());
                    if (child.getName().equals("name")) {
                        bookEntity.setName(child.getValue());
                    }else if (child.getName().equals("author")) {
                        bookEntity.setAuthor(child.getValue());
                    } else if (child.getName().equals("year")) {
                        bookEntity.setYear(child.getValue());
                    }else if (child.getName().equals("price")) {
                        bookEntity.setPrice(child.getValue());
                    }else if (child.getName().equals("language")) {
                        bookEntity.setLanguage(child.getValue());
                    }
                }
                System.out.println("======结束解析第" + (bookList.indexOf(book) + 1)
                        + "书======");
                booksList.add(bookEntity);
                bookEntity = null;
                System.out.println(booksList.size());
                System.out.println(booksList.get(0).getId());
                System.out.println(booksList.get(0).getName());
                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/**
	 * DOM4J解析
	 * JDOM的一种智能分支
	 */
	public void dom4jParsing() {
		// 解析books.xml文件
        // 创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        try {
            // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
            org.dom4j.Document document = reader.read(new File("src/res/books.xml"));
            // 通过document对象获取根节点bookstore
            org.dom4j.Element bookStore = document.getRootElement();
            // 通过element对象的elementIterator方法获取迭代器
            Iterator it = bookStore.elementIterator();
            // 遍历迭代器，获取根节点中的信息（书籍）
            while (it.hasNext()) {
                System.out.println("=====开始遍历某一本书=====");
                org.dom4j.Element book = (org.dom4j.Element) it.next();
                // 获取book的属性名以及 属性值
                List<Attribute> bookAttrs = book.attributes();
                for (Attribute attr : bookAttrs) {
                    System.out.println("属性名：" + attr.getName() + "--属性值："+ attr.getValue());
                }
                Iterator itt = book.elementIterator();
                while (itt.hasNext()) {
                	org.dom4j.Element bookChild = (org.dom4j.Element) itt.next();
                    System.out.println("节点名：" + bookChild.getName() + "--节点值：" + bookChild.getStringValue());
                }
                System.out.println("=====结束遍历某一本书=====");
            }
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
class SAXParserHandler extends DefaultHandler {
    String value = null;
    Book book = null;
    private ArrayList<Book> bookList = new ArrayList<Book>();
    public ArrayList<Book> getBookList() {
        return bookList;
    }

    int bookIndex = 0;
    /**
     * 	 用来标识解析开始
     * @throws org.xml.sax.SAXException 
     */
    @Override
    public void startDocument() throws org.xml.sax.SAXException {
        // TODO Auto-generated method stub
        super.startDocument();
        System.out.println("SAX解析开始");
    }
    
    /**
     * 	用来标识解析结束
     * @throws org.xml.sax.SAXException 
     */
    @Override
    public void endDocument() throws org.xml.sax.SAXException {
        // TODO Auto-generated method stub
        super.endDocument();
        System.out.println("SAX解析结束");
    }
    
    /**
     * 	解析xml元素
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        //调用DefaultHandler类的startElement方法
        try {
			super.startElement(uri, localName, qName, attributes);
		} catch (org.xml.sax.SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (qName.equals("book")) {
            bookIndex++;
            //创建一个book对象
            book = new Book();
            //开始解析book元素的属性
            System.out.println("======================开始遍历某一本书的内容=================");
            //不知道book元素下属性的名称以及个数，如何获取属性名以及属性值
            int num = attributes.getLength();
            for(int i = 0; i < num; i++){
                System.out.print("book元素的第" + (i + 1) +  "个属性名是：" + attributes.getQName(i));
                System.out.println("---属性值是：" + attributes.getValue(i));
                if (attributes.getQName(i).equals("id")) {
                    book.setId(attributes.getValue(i));
                }
            }
        }else if (!qName.equals("name") && !qName.equals("bookstore")) {
            System.out.print("节点名是：" + qName + "---");
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws org.xml.sax.SAXException {
        //调用DefaultHandler类的endElement方法
        super.endElement(uri, localName, qName);
        //判断是否针对一本书已经遍历结束
        if (qName.equals("book")) {
            bookList.add(book);
            book = null;
            System.out.println("======================结束遍历某一本书的内容=================");
        }else if (qName.equals("name")) {
            book.setName(value);
        }else if (qName.equals("author")) {
            book.setAuthor(value);
        }else if (qName.equals("year")) {
            book.setYear(value);
        }else if (qName.equals("price")) {
            book.setPrice(value);
        }else if (qName.equals("language")) {
            book.setLanguage(value);
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws org.xml.sax.SAXException {
        // TODO Auto-generated method stub
        super.characters(ch, start, length);
        value = new String(ch, start, length);
        if (!value.trim().equals("")) {
            System.out.println("节点值是：" + value);
        }
    } 
}
class Book{
	String id;
	String name;
	String author;
	String year;
	String price;
	String language;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
}
