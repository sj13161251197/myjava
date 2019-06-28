package javaBase.inner;

public class TestParcel{
	   public static void main(String[] args){
	      Parce1 p = new Parce1();
	      Contents c = p.cont();
	      System.out.println(c.value());
	      Destination d = p.dest("Cannel_2020");
	      System.out.println(d.readLabel());
	   }
	}
interface Destination{
   String readLabel();
}
interface Contents{
   int value();
}
/**
 * 内中有2个普通方法
 * 2个成员内部类受访问限制
 * @author Administrator
 *
 */
class Parce1{
   private class PContents implements Contents{
      private int i = 2012;
      public int value() {
        return i;
      }
   }
   protected class PDestination implements Destination{
      private String label;
      private PDestination(String label){
        this.label = label;
      }
      public String readLabel() {
        return label;
      }
   }
 
   public  PDestination dest(String label){
      return new PDestination(label);
   }
   public Contents cont(){
      return new PContents();
   }
}
	