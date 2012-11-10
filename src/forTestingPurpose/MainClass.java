package forTestingPurpose;

import java.util.ArrayList;

public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*System.out.println("Working Directory = " + System.getProperty("user.dir"));
		System.out.println("Working Directory = " + System.getProperty("user.name"));
		System.out.println("Working Directory = " + System.getProperty("user.name"));
		System.out.println("Working Directory = " + System.getProperty("user.name"));
		System.out.println("Working Directory = " + System.getProperty("user.name"));*/
		/*Gson gson = new Gson();
		HashMap <String, String> testMap = new HashMap<String, String>();
		testMap .put("1", "abc");
		testMap .put("2", "ezc");
		testMap .put("3", "afc");
		testMap .put("4", "cvc");
		System.out.println(testMap);
		Object obj = (Object)testMap;
		System.out.println(obj);
		String className = obj.getClass().getName();
		System.out.println(className);
		String json = gson.toJson(obj);
		System.out.println(json);
		try {
			System.out.println(gson.fromJson(json, Class.forName(className)));
			
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("aloha");
		list.add("namaskar");
		list.add("tashi delek");
		
		System.out.println(list);
		System.out.println(list.toArray());
		System.out.println(list.toString());
		
	} 
		

}


