package word;

import java.util.*;
import FileIO.FileOpen;

class Counter {// 计算词频
	int i = 1;

	public String toString() {
		return Integer.toString(i);
	}
}

public class WordNum {
	private Map<String, Counter> hm = new HashMap<String, Counter>();// 存放单词及词频
	private List<String> words = new ArrayList<String>();// 存放单词
	private String openFile; // 打开文件名
	public WordNum(String openFile) {
		super();
		this.openFile = openFile;
	}
	public Map<String, Counter> getmap(){
		return hm;
	}
	public void init() {
		
		try {
			words = Arrays.asList(new FileOpen().testFile(openFile));   //得到单词数组转为ArrayList
		} catch (Exception e1) {
		}

		for (int i = 0; i < words.size(); i++) {
			String key = words.get(i);
			if (hm.containsKey(key)) { // 包含该单词时计数器加一
				((Counter) hm.get(key)).i++;

			} else {
				hm.put(key, new Counter());
			}
		}
		
	}
}
