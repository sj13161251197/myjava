import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.apache.commons.jexl3.internal.Engine;



public class Cron {
	public static void main(String[] args) {
		int a=1;
		char b=1;
		System.out.println((char)a);
		System.out.println((int)b);
		String cardid="707d661e";
		System.out.println(Long.parseLong(cardid,16));
		//时间表达式 秒_分_时_日_周_月_年
		//*代表全部 
		//-代表范围
		//?代表每  也在  周和月上  代表每周或者每月
		/*String timeCron="";
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("aa1", 5.2);
		map.put("aa2", 7.2);
		map.put("aa3", 2.2);
		map.put("aa4", 6.2);
		map.put("aa5", 5.9);
		Double aa1 =(Double) map.get("aa1");
		Double aa2 =(Double) map.get("aa2");
		Double aa3 =(Double) map.get("aa3");
		Double aa4 =(Double) map.get("aa4");
		Double aa5 =(Double) map.get("aa5");
		boolean b=((aa1>5.3)||(aa2>6.2))&&((aa3<2.2)&&(aa4>6.2))||(aa5>5.9);
		System.out.println(b);
		String bStr="((aa1>5.3)||(aa2>6.2))&&((aa3<2.2)&&(aa4>6.2))||(aa5>5.9)";
		executeExpression(bStr,map);*/

		//Map<String,Object> map=new HashMap<String,Object>(); 
		//map.put("money",2100);  
		//String expression="money>=2000&&money<=4000";  
		//Object code = executeExpression(expression,map);
		
	}
	public static Object executeExpression(String jexlExpression, Map<String, Object> map) {
		JexlEngine jexlEngine = new Engine();
		JexlExpression expression = jexlEngine.createExpression(jexlExpression);
	    JexlContext jc = new MapContext();  
        for(String key:map.keySet()){  
            jc.set(key, map.get(key));  
        }  
        if(null==expression.evaluate(jc)){  
            return "";  
        }  
        return expression.evaluate(jc);  
	
	}
	
	
}
