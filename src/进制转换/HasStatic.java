package 进制转换;


public class HasStatic{
	public static boolean Find(int target, int[][] a) {
        boolean c=false;
        
        for(int i=0;i<a.length;i++){
            int[] b=a[i];
                
            for(int j=0;j<b.length;j++){
                if(target==b[j]){
                    c=true;
                    break;
                }
            }
            if(c){
                  break; 
            }
        }
        return c;
        
    }
	public static void main(String[] args) {
		int[][] a={{1,2},{3,4},{5,6}};
		boolean find = Find(5,a);
		System.out.println(find);
	}
 } 