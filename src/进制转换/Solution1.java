package 进制转换;

public class Solution1 {
    
	  public static boolean Find(int target, int[][] arr) {
	        boolean flag=false;
	        
	        for(int i=0;i<arr.length;i++){
	            int[] b=arr[i];
	                
	            for(int j=0;j<b.length;j++){
	                if(target==b[j]){
	                    flag=true;
	                    break;
	                }
	            }
	            if(flag){
	                  break;  
	            } 
	        }
	        return flag;
	        
	    }
	  public static void main(String[] args) {
			int[][] a={{1,2},{3,4},{5,6}};
			boolean find = Find(5,a);
			System.out.println(find);
		}
	};
	