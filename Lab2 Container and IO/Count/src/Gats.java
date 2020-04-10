import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.io.File;

public class Gats {
	private static String readFromTxt(String filename) throws Exception {
		Reader reader = null;
		try {
			StringBuffer buf = new StringBuffer();
			char[] chars = new char[1024];
			reader = new FileReader(filename);
			int readed = reader.read(chars);
			while (readed != -1) {
				buf.append(chars, 0, readed);
				readed = reader.read(chars);
			}
			return buf.toString();
		} finally {
			close(reader);
		}
	}
	
	private static String readFromTxt1(String filename) throws IOException {
		StringBuffer buffer = new StringBuffer();
        BufferedReader bf= new BufferedReader(new FileReader(filename));
        String s = null;
        while((s = bf.readLine())!=null){//使用readLine方法，一次读一行
            buffer.append(s.trim());
        }
        return buffer.toString();
	}
	
	private static Map<String,Integer> stringToMap(String str){
		Map<String,Integer> map = new HashMap<>();
		String contents[] = str.split("\\s+");
		for(int i=0;i<contents.length;i++) {
			String temp = contents[i];
			//System.out.print(i+"----"+contents[i]);
			if(map.containsKey(temp)) {
				Integer ex = map.get(temp)+1;
				map.put(temp, ex);
			}else {
				map.put(temp,1);
			}
		}
		return map;
	}
	
	private static List<Entry<String,Integer>> sort(Map<String,Integer> record) {
			List<Entry<String,Integer>> list = new ArrayList<Entry<String,Integer>>(record.entrySet());
			Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
				public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
	                return o2.getValue().compareTo(o1.getValue());
	            }
	        });
			return list;
	}
	
	private static void close(Closeable inout) {
			if (inout != null) {
				try {
					inout.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
    private static void output(List<Entry<String,Integer>> list, String filename) throws IOException {	
		File outputFile = new File(filename);
		if(!outputFile.exists()) {
			outputFile.createNewFile();
		}
		OutputStream os = new FileOutputStream(outputFile);
		StringBuffer temp = new StringBuffer();
		for (Entry<String, Integer> e: list) {
			temp.append(e.getKey() + " " + e.getValue()+"\r\n");
		}
		byte data[] = temp.toString().getBytes();
		os.write(data);
		os.close();
	}
		
	public static void main(String args[]) throws Exception {
		Map<String, Integer> map = new HashMap<>();
		String txt = "";
		String input = "C:/Users/lenovo/Desktop/theGreatGatsby.txt";
		String output = "C:/Users/lenovo/Desktop/output.txt";
		txt =  readFromTxt(input);
		map = stringToMap(txt);
		List<Entry<String,Integer>> list = sort(map);
		output(list,output);
	}
}