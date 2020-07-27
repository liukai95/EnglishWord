package word;

import java.util.*;
import FileIO.FileOpen;

class Counter {// �����Ƶ
	int i = 1;

	public String toString() {
		return Integer.toString(i);
	}
}

public class WordNum {
	private Map<String, Counter> hm = new HashMap<String, Counter>();// ��ŵ��ʼ���Ƶ
	private List<String> words = new ArrayList<String>();// ��ŵ���
	private String openFile; // ���ļ���
	public WordNum(String openFile) {
		super();
		this.openFile = openFile;
	}
	public Map<String, Counter> getmap(){
		return hm;
	}
	public void init() {
		
		try {
			words = Arrays.asList(new FileOpen().testFile(openFile));   //�õ���������תΪArrayList
		} catch (Exception e1) {
		}

		for (int i = 0; i < words.size(); i++) {
			String key = words.get(i);
			if (hm.containsKey(key)) { // �����õ���ʱ��������һ
				((Counter) hm.get(key)).i++;

			} else {
				hm.put(key, new Counter());
			}
		}
		
	}
}
