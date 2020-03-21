package javaHomework3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CountString {
public static void CountString(String str) {
	HashMap<String,Integer> map = new HashMap<>();
	String[] contents = str.split("@");
    for(int i=0;i<contents.length;i++) {
         if(map.containsKey(contents[i])) {
        	 map.put(contents[i], map.get(contents[i])+1);
         }else {
        	 map.put(contents[i], 1);
         }
    }
    Set keys = map.keySet();
    for(Iterator iter = keys.iterator();iter.hasNext();) {
    	String keyStr = (String) iter.next();
    	System.out.println("<"+keyStr+">=<"+map.get(keyStr)+">");
    }
 }
public static void CountString1(String str) {
	String[] contents = str.split("@");
	Set<String> set = new HashSet<>();
	for(int i=0;i<contents.length;i++) {
		set.add(contents[i]);
	}
	Iterator it = set.iterator();
	
    while (it.hasNext()) {
    	int counts = 0;
    	String nextKey = (String) it.next();
        for(int i=0;i<contents.length;i++) {
    		if(contents[i].equals(nextKey)){
    			counts++;
    		}
    	}
        System.out.println("<"+nextKey+">=<"+counts+">");
    }
}
public static void main(String[] args) {
	System.out.println("First method:");
	CountString("123@123@456@678");
	System.out.println("Second method:");
	CountString1("123@123@456@678");
}
}

