/**
 * https://www.cnblogs.com/longqingyang/p/5579889.html
 * @author Administrator
 *
 */
返回主页
龍清扬
博客园首页新随笔联系管理订阅订阅
随笔- 73  文章- 0  评论- 21 
java文件上传和下载
简介
　　文件上传和下载是java web中常见的操作，文件上传主要是将文件通过IO流传放到服务器的某一个特定的文件夹下，而文件下载则是与文件上传相反，将文件从服务器的特定的文件夹下的文件通过IO流下载到本地。

　　对于文件上传，浏览器在上传的过程中是将文件以流的形式提交到服务器端的，如果直接使用Servlet获取上传文件的输入流然后再解析里面的请求参数是比较麻烦，所以一般选择采用apache的开源工具common-fileupload这个文件上传组件。这个common-fileupload上传组件的jar包可以去apache官网上面下载，也可以在struts的lib文件夹下面找到，struts上传的功能就是基于这个实现的。common-fileupload是依赖于common-io这个包的，所以还需要下载这个包。

文件上传
　　1、文件上传页面和消息提示页面

　　upload.jsp页面的代码如下：

复制代码
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>文件上传</title>
   </head>
  
   <body>
     <form action="${pageContext.request.contextPath}/servlet/uploadHandleServlet2" enctype="multipart/form-data" method="post"         上传用户：<input type="text" name="username"><br/>
         上传文件1：<input type="file" name="file1"><br/>
         上传文件2：<input type="file" name="file2"><br/>
         <input type="submit" value="提交">
     </form>
   </body>
</html>
复制代码
在文件上传的页面要用enctype="multipart/form-data" method="post"来表示进行文件上传。

　　2、处理文件上传的Servlet

复制代码
public class UploadHandleServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        File file = new File(savePath);
        if(!file.exists()&&!file.isDirectory()){
            System.out.println("目录或文件不存在！");
            file.mkdir();
        }
        //消息提示
        String message = "";
        try {
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
            //解决上传文件名的中文乱码
            fileUpload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if(!fileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return;
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = fileUpload.parseRequest(request);
            for (FileItem item : list) {
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    String value1 = new String(name.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(name+"  "+value);
                    System.out.println(name+"  "+value1);
                }else{
                    //如果fileitem中封装的是上传文件，得到上传的文件名称，
                    String fileName = item.getName();
                    System.out.println(fileName);
                    if(fileName==null||fileName.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    fileName = fileName.substring(fileName.lastIndexOf(File.separator)+1);
                    //获取item中的上传文件的输入流
                    InputStream is = item.getInputStream();
                    //创建一个文件输出流
                    FileOutputStream fos = new FileOutputStream(savePath+File.separator+fileName);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int length = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while((length = is.read(buffer))>0){
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        fos.write(buffer, 0, length);
                    }
                    //关闭输入流
                    is.close();
                    //关闭输出流
                    fos.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    message = "文件上传成功";
                }
            }
        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            message = "文件上传失败";
        }
        request.setAttribute("message",message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
}
复制代码
3、文件上传的细节

　　上述的代码虽然可以成功将文件上传到服务器上面的指定目录当中，但是文件上传功能有许多需要注意的小细节问题，以下列出的几点需要特别注意的：

　　（1）、为保证服务器安全，上传文件应该放在外界无法直接访问的目录下，比如放于WEB-INF目录下。

　　（2）、为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名。

　　（3）、为防止一个目录下面出现太多文件，要使用hash算法打散存储。

　　（4）、要限制上传文件的最大值。

　　（5）、要限制上传文件的类型，在收到上传文件名时，判断后缀名是否合法。

　　4、改进后的servlet

复制代码
public class UploadHandleServlet1 extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        //上传时生成的临时文件保存目录
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
        File file = new File(tempPath);
        if(!file.exists()&&!file.isDirectory()){
            System.out.println("目录或文件不存在！");
            file.mkdir();
        }
        //消息提示
        String message = "";
        try {
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
            diskFileItemFactory.setSizeThreshold(1024*100);
            //设置上传时生成的临时文件的保存目录
            diskFileItemFactory.setRepository(file);
            //2、创建一个文件上传解析器
            ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
            //解决上传文件名的中文乱码
            fileUpload.setHeaderEncoding("UTF-8");
            //监听文件上传进度
            fileUpload.setProgressListener(new ProgressListener(){
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
                }
            });
            //3、判断提交上来的数据是否是上传表单的数据
            if(!fileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return;
            }
            //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
            fileUpload.setFileSizeMax(1024*1024);
            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
            fileUpload.setSizeMax(1024*1024*10);
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = fileUpload.parseRequest(request);
            for (FileItem item : list) {
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    String value1 = new String(name.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(name+"  "+value);
                    System.out.println(name+"  "+value1);
                }else{
                    //如果fileitem中封装的是上传文件，得到上传的文件名称，
                    String fileName = item.getName();
                    System.out.println(fileName);
                    if(fileName==null||fileName.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    fileName = fileName.substring(fileName.lastIndexOf(File.separator)+1);
                    //得到上传文件的扩展名
                    String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
                    if("zip".equals(fileExtName)||"rar".equals(fileExtName)||"tar".equals(fileExtName)||"jar".equals(fileExtName)){
                        request.setAttribute("message", "上传文件的类型不符合！！！");
                        request.getRequestDispatcher("/message.jsp").forward(request, response);
                        return;
                    }
                    //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                    System.out.println("上传文件的扩展名为:"+fileExtName);
                    //获取item中的上传文件的输入流
                    InputStream is = item.getInputStream();
                    //得到文件保存的名称
                    fileName = mkFileName(fileName);
                    //得到文件保存的路径
                    String savePathStr = mkFilePath(savePath, fileName);
                    System.out.println("保存路径为:"+savePathStr);
                    //创建一个文件输出流
                    FileOutputStream fos = new FileOutputStream(savePathStr+File.separator+fileName);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int length = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while((length = is.read(buffer))>0){
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        fos.write(buffer, 0, length);
                    }
                    //关闭输入流
                    is.close();
                    //关闭输出流
                    fos.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    message = "文件上传成功";
                }
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            request.setAttribute("message", "单个文件超出最大值！！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }catch (FileUploadBase.SizeLimitExceededException e) {
            e.printStackTrace();
            request.setAttribute("message", "上传文件的总的大小超出限制的最大值！！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            message = "文件上传失败";
        }
        request.setAttribute("message",message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    //生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
    public String mkFileName(String fileName){
        return UUID.randomUUID().toString()+"_"+fileName;
    }
    public String mkFilePath(String savePath,String fileName){
        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = fileName.hashCode();
        int dir1 = hashcode&0xf;
        int dir2 = (hashcode&0xf0)>>4;
        //构造新的保存目录
        String dir = savePath + "\\" + dir1 + "\\" + dir2;
        //File既可以代表文件也可以代表目录
        File file = new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
        return dir;
    }
}
复制代码
5、如果在文件上传中IO流成为了系统的性能瓶颈，可以考虑使用NIO来提高性能。改进servlet代码如下：

复制代码
public class UploadHandleServlet2 extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        //上传时生成的临时文件保存目录
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
        File file = new File(tempPath);
        if(!file.exists()&&!file.isDirectory()){
            System.out.println("目录或文件不存在！");
            file.mkdir();
        }
        //消息提示
        String message = "";
        try {
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
            diskFileItemFactory.setSizeThreshold(1024*100);
            //设置上传时生成的临时文件的保存目录
            diskFileItemFactory.setRepository(file);
            //2、创建一个文件上传解析器
            ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
            //解决上传文件名的中文乱码
            fileUpload.setHeaderEncoding("UTF-8");
            //监听文件上传进度
            fileUpload.setProgressListener(new ProgressListener(){
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
                }
            });
            //3、判断提交上来的数据是否是上传表单的数据
            if(!fileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return;
            }
            //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
            fileUpload.setFileSizeMax(1024*1024);
            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
            fileUpload.setSizeMax(1024*1024*10);
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = fileUpload.parseRequest(request);
            for (FileItem item : list) {
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    String value1 = new String(name.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(name+"  "+value);
                    System.out.println(name+"  "+value1);
                }else{
                    //如果fileitem中封装的是上传文件，得到上传的文件名称，
                    String fileName = item.getName();
                    System.out.println(fileName);
                    if(fileName==null||fileName.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    fileName = fileName.substring(fileName.lastIndexOf(File.separator)+1);
                    //得到上传文件的扩展名
                    String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
                    if("zip".equals(fileExtName)||"rar".equals(fileExtName)||"tar".equals(fileExtName)||"jar".equals(fileExtName)){
                        request.setAttribute("message", "上传文件的类型不符合！！！");
                        request.getRequestDispatcher("/message.jsp").forward(request, response);
                        return;
                    }
                    //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                    System.out.println("上传文件的扩展名为:"+fileExtName);
                    //获取item中的上传文件的输入流
                    InputStream fis = item.getInputStream();
                    //得到文件保存的名称
                    fileName = mkFileName(fileName);
                    //得到文件保存的路径
                    String savePathStr = mkFilePath(savePath, fileName);
                    System.out.println("保存路径为:"+savePathStr);
                    //创建一个文件输出流
                    FileOutputStream fos = new FileOutputStream(savePathStr+File.separator+fileName);
                    //获取读通道
                    FileChannel readChannel = ((FileInputStream)fis).getChannel();
                    //获取读通道
                    FileChannel writeChannel = fos.getChannel();
                    //创建一个缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    //判断输入流中的数据是否已经读完的标识
                    int length = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while(true){
                        buffer.clear();
                        int len = readChannel.read(buffer);//读入数据
                        if(len < 0){
                            break;//读取完毕 
                        }
                        buffer.flip();
                        writeChannel.write(buffer);//写入数据
                    }
                    //关闭输入流
                    fis.close();
                    //关闭输出流
                    fos.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    message = "文件上传成功";
                }
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            request.setAttribute("message", "单个文件超出最大值！！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }catch (FileUploadBase.SizeLimitExceededException e) {
            e.printStackTrace();
            request.setAttribute("message", "上传文件的总的大小超出限制的最大值！！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            message = "文件上传失败";
        }
        request.setAttribute("message",message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    //生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
    public String mkFileName(String fileName){
        return UUID.randomUUID().toString()+"_"+fileName;
    }
    public String mkFilePath(String savePath,String fileName){
        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = fileName.hashCode();
        int dir1 = hashcode&0xf;
        int dir2 = (hashcode&0xf0)>>4;
        //构造新的保存目录
        String dir = savePath + "\\" + dir1 + "\\" + dir2;
        //File既可以代表文件也可以代表目录
        File file = new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
        return dir;
    }
}
复制代码
文件下载
　　1、列出提供下载的文件资源

　　要将Web应用系统中的文件资源提供给用户进行下载，首先我们要有一个页面列出上传文件目录下的所有文件，当用户点击文件下载超链接时就进行下载操作，编写一个ListFileServlet，用于列出Web应用系统中所有下载文件。

　　ListFileServlet代码如下

复制代码
public class ListFileServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //获取上传文件的目录
        String uploadFilePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        //存储要下载的文件名
        Map<String, String> fileMap = new HashMap<String, String>();
        //递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
        fileList(new File(uploadFilePath),fileMap);
        //将Map集合发送到listfile.jsp页面进行显示
        request.setAttribute("fileMap", fileMap);
        request.getRequestDispatcher("/listfile.jsp").forward(request, response);

    }
    //递归遍历指定目录下的所有文件
    public void fileList(File file,Map fileMap){
        //如果file代表的不是一个文件，而是一个目录
        if(!file.isFile()){
            //列出该目录下的所有文件和目录
            File[] files = file.listFiles();
            //遍历files[]数组
            for (File file2 : files) {
                System.out.println(file2.getName());
                //递归
                fileList(file2, fileMap);
            }
        }else{
              /* 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
                 file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：9349249849-88343-8344_阿_凡_达.avi
                  那么file.getName().substring(file.getName().indexOf("_")+1)处理之后就可以得到阿_凡_达.avi部分
              */
            String realName = file.getName().substring(file.getName().lastIndexOf("_")+1);
            //file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
            fileMap.put(file.getName(), realName);
        }
    }
}
复制代码
说明一下，一般文件路径在数据库中保存，然后再数据库中查询结果在页面显示。

　　listfile.jsp页面

复制代码
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE HTML>
 <html>
   <head>
     <title>下载文件显示页面</title>
  </head>
   
   <body>
      <!-- 遍历Map集合 -->
     <c:forEach var="me" items="${fileMap}">
         <c:url value="/servlet/downLoadServlet" var="downurl">
             <c:param name="filename" value="${me.key}"></c:param>
         </c:url>
         ${me.value}<a href="${downurl}">下载</a>
         <br/>
     </c:forEach>
   </body>
 </html>
复制代码
2、文件下载

　　DownLoadServlet的代码如下：

复制代码
public class DownLoadServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到要下载的文件名
        String fileName = request.getParameter("filename");
        fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
        //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
        String fileSaveRootPath=this.getServletContext().getRealPath("/WEB-INF/upload");
        //        处理文件名
         String realname = fileName.substring(fileName.indexOf("_")+1);
        //通过文件名找出文件的所在目录
        String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
        //得到要下载的文件
        File file = new File(path+File.separator+fileName);
        //如果文件不存在
        if(!file.exists()){
            request.setAttribute("message", "您要下载的资源已被删除！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        
         //设置响应头，控制浏览器下载该文件
         response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
         //读取要下载的文件，保存到文件输入流
         FileInputStream fis = new FileInputStream(path + File.separator + fileName);
         //创建输出流
         OutputStream fos = response.getOutputStream();
         //设置缓存区
         ByteBuffer buffer = ByteBuffer.allocate(1024);
         //输入通道
         FileChannel readChannel = fis.getChannel();
         //输出通道
         FileChannel writeChannel = ((FileOutputStream)fos).getChannel();
         while(true){
             buffer.clear();
             int len = readChannel.read(buffer);//读入数据
             if(len < 0){
                 break;//传输结束
             }
             buffer.flip();
             writeChannel.write(buffer);//写入数据
         }
         //关闭输入流
         fis.close();
         //关闭输出流
         fos.close();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    //通过文件名和存储上传文件根目录找出要下载的文件的所在路径
    public String findFileSavePathByFileName(String fileName,String fileSaveRootPath){
        int hashcode = fileName.hashCode();
        int dir1 = hashcode&0xf;
        int dir2 = (hashcode&0xf0)>>4;
        String dir = fileSaveRootPath + "\\" + dir1 + "\\" + dir2;
        File file = new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
        return dir;
    }
}
复制代码
3、如果IO成为系统的瓶颈，可以考虑使用NIO来实现下载，提供系统性能，改进后的DownloadServlet代码如下：

复制代码
public class DownLoadServlet1 extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到要下载的文件名
        String fileName = request.getParameter("filename");
        fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
        //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
        String fileSaveRootPath=this.getServletContext().getRealPath("/WEB-INF/upload");
        //        处理文件名
         String realname = fileName.substring(fileName.indexOf("_")+1);
        //通过文件名找出文件的所在目录
        String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
        //得到要下载的文件
        File file = new File(path+File.separator+fileName);
        //如果文件不存在
        if(!file.exists()){
            request.setAttribute("message", "您要下载的资源已被删除！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        
         //设置响应头，控制浏览器下载该文件
         response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
         //读取要下载的文件，保存到文件输入流
         FileInputStream in = new FileInputStream(path + File.separator + fileName);
         //创建输出流
         OutputStream os = response.getOutputStream();
         //设置缓存区
         byte[] bytes = new byte[1024];
         int len = 0;
         while((len = in.read(bytes))>0){
             os.write(bytes);
         }
         //关闭输入流
         in.close();
         //关闭输出流
         os.close();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    //通过文件名和存储上传文件根目录找出要下载的文件的所在路径
    public String findFileSavePathByFileName(String fileName,String fileSaveRootPath){
        int hashcode = fileName.hashCode();
        int dir1 = hashcode&0xf;
        int dir2 = (hashcode&0xf0)>>4;
        String dir = fileSaveRootPath + "\\" + dir1 + "\\" + dir2;
        File file = new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
        return dir;
    }
}
复制代码
 

标签: java
好文要顶 关注我 收藏该文    
龍清扬
关注 - 17
粉丝 - 43
+加关注
4 0
« 上一篇：JAVA解析xml的五种方式比较
» 下一篇：Java的导入与导出Excel
posted @ 2016-06-13 10:57 龍清扬 阅读(45342) 评论(8) 编辑 收藏

评论
   #1楼 2016-11-24 21:45 | Lucy_share  
收藏了~
支持(0)反对(0)
   #2楼 2016-12-26 16:38 | 釧钏汌川  
博主，为什么下载这个功能一直提示 文件已被删除 而且前台传回的数据没法转格式，一直是乱码
支持(0)反对(1)
   #3楼 2017-02-10 18:02 | 英雄辈出  
//创建输出流
OutputStream fos = response.getOutputStream();
//设置缓存区
ByteBuffer buffer = ByteBuffer.allocate(1024);
//输入通道
FileChannel readChannel = fis.getChannel();
//输出通道
FileChannel writeChannel = ((FileOutputStream)fos).getChannel();
文件下载，第二部分（DownLoadServlet 类），(FileOutputStream)fos，强制转换异常，真心求教
支持(3)反对(0)
   #4楼 2017-02-10 18:04 | 英雄辈出  
楼主这篇文章很不错，注释真是够详细，，，大赞！！！
支持(0)反对(0)
   #5楼 2017-09-06 15:05 | 折翼流萤  
楼主文章写的很好，已收藏，但是三楼出现的问题有好的解决方法吗？如果新增new file再delete的话，可以实现，但有点得不偿失。望指教！
支持(0)反对(0)
   #6楼 2018-04-19 16:13 | liyutao  
NIO方式上传的时候强转类型会报错，应该用下面这种方式

//获取读通道
ReadableByteChannel readChannel = Channels.newChannel(inputStream);
//获取读通道
WritableByteChannel writeChannel = hannels.newChannel(fileOutputStream);
支持(0)反对(0)
   #7楼 2018-09-06 18:53 | 名震、超大陆  
请问你这个文件上传，是上传到数据库里面的吗。求解
支持(0)反对(0)
   #8楼 2018-09-18 16:19 | 顾及i  
@ 名震、超大陆
不是，文件是上传到服务器的，数据库只存入一个路径
支持(0)反对(0)
刷新评论刷新页面返回顶部
注册用户登录后才能发表评论，请 登录 或 注册，访问网站首页。
【推荐】超50万C++/C#源码: 大型实时仿真组态图形源码
【推荐】专业便捷的企业级代码托管服务 - Gitee 码云
【活动】2019第四届全球人工技术大会解码“智能+时代”
相关博文：
· java文件上传和下载
· java文件上传和下载
· java实现文件上传和下载
· Java 实现ftp 文件上传、下载和删除
· java 文件的上传和下载
最新新闻：
· 为什么说担心苹果掉队5G是多余的事？
· Python之父发声：我们能为中国的“996”程序员做什么？
· 美国业者呼吁政府加大投入 应对中国芯片行业兴起
· 新京报：“996制”遭抵制，加班文化需重新审视
· 波音暂缩737型客机月产量10架 专注737MAX复飞工作
» 更多新闻...
昵称：龍清扬
园龄：2年10个月
粉丝：43
关注：17
+加关注
<	2019年4月	>
日	一	二	三	四	五	六
31	1	2	3	4	5	6
7	8	9	10	11	12	13
14	15	16	17	18	19	20
21	22	23	24	25	26	27
28	29	30	1	2	3	4
5	6	7	8	9	10	11
搜索

 

 
常用链接
我的随笔
我的评论
我的参与
最新评论
我的标签
我的标签
java(20)
JS(11)
MySQL(6)
Linux入门学习(6)
数据结构(5)
spring(4)
JAVA并发操作(3)
Java设计模式(3)
RabbitMQ(2)
WebSocket(2)
更多
随笔档案
2018年5月 (1)
2018年1月 (2)
2017年11月 (2)
2017年10月 (1)
2017年8月 (2)
2017年7月 (2)
2017年6月 (12)
2017年5月 (2)
2017年4月 (1)
2017年2月 (3)
2016年11月 (2)
2016年10月 (2)
2016年9月 (2)
2016年8月 (8)
2016年7月 (6)
2016年6月 (20)
2016年5月 (5)
积分与排名
积分 -	47465
排名 -	11543
最新评论
1. Re:XML解析——Java中XML的四种解析方式
@小小牙沙雕网友...
--MicroCat
2. Re:XML解析——Java中XML的四种解析方式
Let's try GraphQuery, a more powerful HTML/XML parsing language. Project:...
--gomaster
3. Re:java文件上传和下载
@名震、超大陆不是，文件是上传到服务器的，数据库只存入一个路径...
--顾及i
4. Re:java文件上传和下载
请问你这个文件上传，是上传到数据库里面的吗。求解
--名震、超大陆
5. Re:java文件上传和下载
NIO方式上传的时候强转类型会报错，应该用下面这种方式//获取读通道ReadableByteChannel readChannel = Channels.newChannel(inputStream)......
--liyutao
阅读排行榜
1. XML解析——Java中XML的四种解析方式(131715)
2. java文件上传和下载(45341)
3. Spring框架中的定时器 如何使用和配置(29879)
4. java--Quartz 定时执行(18605)
5. JAVA解析xml的五种方式比较(14699)
评论排行榜
1. java文件上传和下载(8)
2. XML解析——Java中XML的四种解析方式(3)
3. JAVA解析xml的五种方式比较(2)
4. 基本网络概念(2)
5. 常用的SpringMVC注解(1)
推荐排行榜
1. XML解析——Java中XML的四种解析方式(17)
2. Spring框架中的定时器 如何使用和配置(4)
3. java文件上传和下载(4)
4. java--Quartz 定时执行(3)
5. Linux jar包 后台运行(2)
Copyright ©2019 龍清扬