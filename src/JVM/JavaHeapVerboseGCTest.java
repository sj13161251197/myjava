package JVM;

import java.util.HashMap;
import java.util.Map;

public class JavaHeapVerboseGCTest {

    private static Map<String, String> mapContainer = new HashMap<String, String>();

    /**-server -Xms1024m  -Xmx1024m -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:C:/temp/gc.log
*   @param args
    */
    public static void main(String[] args) {
    	System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);
         System.out.println("Java 7 HotSpot Verbose GC Test Program v1.0");
         System.out.println("Author: Pierre-Hugues Charbonneau");
         System.out.println("http://javaeesupportpatterns.blogspot.com/");

         String stringDataPrefix = "stringDataPrefix";

         // Load Java Heap with 3 M java.lang.String instances
       /*  for (int i=0; i<3000000; i++) {
              String newStringData = stringDataPrefix + i;                 
              mapContainer.put(newStringData, newStringData);
         }
*/
         System.out.println("MAP size: "+mapContainer.size()); 
         System.gc(); // Explicit GC!

         // Remove 2 M out of 3 M           
       /*  for (int i=0; i<2000000; i++) {
              String newStringData = stringDataPrefix + i; 
              mapContainer.remove(newStringData);
         }
*/
         System.out.println("MAP size: "+mapContainer.size());  
         System.gc();
         System.out.println("End of program!");      

    }
}