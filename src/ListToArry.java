import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ListToArry {
	
	
	public static void main(String[] args) {
		List<String> a=new ArrayList<String>();
		String name="";
		a.add("bs2");
		a.add("bs3");
		a.add("bs5");
		a.add("bs4");
		a.add("bs1");
		for(String user:a){
    		name=name+user+",";
    	}
		if(name.contains(",")){
			System.out.println(name.substring(0, name.length()-1));;
		}
		System.out.println(a.toString());
		/*Map<String,Integer> map=new HashMap<String,Integer>();
		System.out.println(map.get("dfs"));
		System.out.println(map.put("aaa1", 5));
		System.out.println(map.put("aaa2", 5));
		System.out.println(map.put("aaa1", 5));
		System.out.println(map.put("aaa2", 5)); 
		Integer put = map.put("aaa2", 5);*/
		/*List<stu> s=new ArrayList<stu>();
		stu stu11 = new stu("1","sdf");
		s.add(stu11 );
		stu11.name="dfvdbgdf";
		System.out.println(s.get(0).name);
		System.out.println(new stu("1",null).name+new stu("1","sdf").name);
		Set<String> set=new HashSet<String>();*/
		/*System.out.println(set.add("bbc"));
		System.out.println(set.add("bba"));
		System.out.println(set.add("bbc"));
		System.out.println(set.add("bba"));*/
		/*Set<stu> set1=new HashSet<stu>();
		System.out.println(set1.add(new stu("1","sdf")));
		System.out.println(set1.add(new stu("1","sdf")));
		System.out.println(set1.add(new stu("1","sdf")));
		System.out.println(set1.add(new stu("1","sdf")));*/
		//listToSet();
	}
	private static void OrtherMenthed() {
		List<Integer> list=new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(7); 
		list.add(5);
		Set<Integer> staffsSet = new HashSet<Integer>(list);
		list.add(4);
		list.add(5);
		//Collections.sort(list);
		System.out.println(list.toString());
		
		staffsSet.add(4);
		staffsSet.add(5);
		staffsSet.add(5);
		System.out.println(staffsSet.toString());
	}
	void arrToSet(){
		String[] staffs = new String[]{"Tom", "Bob", "Jane"};
		Set<String> staffsSet = new HashSet<>(Arrays.asList(staffs));
		staffsSet.add("Mary"); // ok
		staffsSet.remove("Tom"); // ok
	}
	void arrToList(){
		String[] staffs = new String[]{"Tom", "Bob", "Jane"};
		List staffsList = Arrays.asList(staffs);
	}
	void listToArr() {
		String[] staffs = new String[]{"Tom", "Bob", "Jane"};
		List staffsList = Arrays.asList(staffs);

		Object[] result = staffsList.toArray();
	}
	void setToArr() {
		String[] staffs = new String[]{"Tom", "Bob", "Jane"};
		Set<String> staffsSet = new HashSet<>(Arrays.asList(staffs));

		Object[] result = staffsSet.toArray();
	}
	void setToList() {
		String[] staffs = new String[]{"Tom", "Bob", "Jane"};
		Set<String> staffsSet = new HashSet<>(Arrays.asList(staffs));

		List<String> result = new ArrayList<>(staffsSet);
		
	}
	static void listToSet() {
		List<Integer> list=new ArrayList<Integer>();
		list.add(1);
		list.add(3);
		list.add(5);
		list.add(7);
		Set<Integer> staffsSet = new HashSet<Integer>(list);
		List<Integer> result = new ArrayList<>(staffsSet);
		boolean remove2 = staffsSet.remove(3);
		System.out.println(staffsSet); 
		boolean remove = result.remove((Integer)3);
		
		System.out.println(result);
	}
	static void mapChange() {
		Map<String,String> map=new HashMap<String,String>();
		System.out.println(map.get("aa")==null);
		map.put("aa", null);
		System.out.println(map.get("aa")==null);
		map.put("aa", "dd");
		System.out.println(map.get("aa")==null);
	}
	
}
class stu{
	String id;
	String name;
	public stu(String id, String name) {
		super();
		this.id = id;
		this.name = name; 
	} 
	public boolean equals(stu b){
		System.out.println("---------------");
		return !this.id.equals(b.id);
	}
	
}
