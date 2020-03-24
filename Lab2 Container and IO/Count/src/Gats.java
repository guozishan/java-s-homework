import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
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
	private static Map<String,Integer> readByFileReader(String filename) throws Exception {
		Map<String,Integer> map = new HashMap<>();
		Reader reader = null;
		String artical = "";
		try {
			StringBuffer buf = new StringBuffer();
			char[] chars = new char[1024];
			reader = new FileReader(filename);
			int readed = reader.read(chars);
			while (readed != -1) {
				buf.append(chars, 0, readed);
				readed = reader.read(chars);
			}
			artical = buf.toString();
		} finally {
			close(reader);
		}
		StringTokenizer st = new StringTokenizer(artical.toString()," ,?.!:\\\"\\\"''\\n#");
		while(st.hasMoreElements()) {
			String str = st.nextToken().toLowerCase(); 
			if(map.containsKey(str)) {
				Integer ex = map.get(str)+1;
				map.put(str, ex);
			}else {
				map.put(str,1);
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
				temp.append(e.getKey() + " " + e.getValue()+"\n");
			}
			byte data[] = temp.toString().getBytes();
			os.write(data);
			os.close();
		}
		
		public static void main(String args[]) throws Exception {
			Map<String, Integer> record = new HashMap<>();
			String artical = "C:/Users/lenovo/Desktop/theGreatGatsby.txt";
			String output = "C:/Users/lenovo/Desktop/output.txt";
			record =  readByFileReader(artical);
			List<Entry<String,Integer>> list = sort(record);
			output(list,output);
		}
}