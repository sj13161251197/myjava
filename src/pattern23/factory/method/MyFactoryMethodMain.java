package pattern23.factory.method;

/** 
 * 工厂方法模式_消费者类 
 *  工厂根据不同的参数请求只负责分发到具体的车间，车间进行具体的处理（不同的参数可以说对应不同的车间）
 * @author popkidorc 
 *  
 */  
public class MyFactoryMethodMain {  
  
    public static void main(String[] args) {  
        IMyMessageFactory myMessageFactory = new MyMessageFactory();  
        IMyMessage myMessage;  
        // 对于这个消费者来说，不用知道如何生产message这个产品，耦合度降低  
        try {  
            // 先来一个短信通知  
            myMessage = myMessageFactory.createMessage("SMS");  
            myMessage.sendMesage();  
  
            // 来一个oa待办  
            
            myMessage = myMessageFactory.createMessage("OA");  
            myMessage.sendMesage();  
  
            // 来一个邮件通知  
            myMessage = myMessageFactory.createMessage("EMAIL");  
            myMessage.sendMesage();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}