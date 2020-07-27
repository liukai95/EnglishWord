package word;

import java.io.*;

import java.util.*;
import FileIO.FileOpen;

public class TiquWord {
	private List<String> words = new ArrayList<String>();// ������е���
	private List<String> words2 = new ArrayList<String>();// ������е���
	private Map<String, StringBuffer> lenWord = new HashMap<String, StringBuffer>();// ��ŵ��ʼ�����
	private String openFile; // ���ļ���
	private BufferedReader br;// ������

	public TiquWord(String openFile) {
		super();
		this.openFile = openFile;
	}

	public void init() {
		try {
			words = Arrays.asList(new FileOpen().testFile(openFile));   //�õ���������תΪArrayList

			for (int i = 0; i < words.size(); i++) {
				words2.add(words.get(i));// �����е��ʰ����Ƚ��з��࣬�����ѯ
			}		
			HashSet<String> hs = new HashSet<String>(words2); // ����ȥ���ظ��ĵ��ʣ�������ȡ
			words2.clear();
			words2.addAll(hs);
			for (int i = 0; i < words2.size(); i++) { // �����е��ʰ����Ƚ��з��࣬�����ѯ
				String key = words2.get(i).length() + "";
				if (lenWord.containsKey(key)) {
					lenWord.get(key).append("\n" + words2.get(i));

				} else {
					lenWord.put(key, new StringBuffer("" + words2.get(i)));
				}
			}
			br.close();
		}catch (Exception e1) {}

	}

	public String lengthWord(int testLen) {
		if (lenWord.containsKey(testLen + "")) {
			String[] l=lenWord.get(testLen + "").toString().split("\n");
			return "����Ϊ"+testLen+"�ĵ��ʸ���Ϊ"+l.length+"��\n"+lenWord.get(testLen + "").toString(); // �����Ž��зָ�
			
		} else {
			
			return ("����Ϊ" + testLen + "�ĵ��ʸ���Ϊ" + 0);
		}

	}

	public String htWord(String str1, String str2) {
		int number = 0;
		int len = 0;
		StringBuffer word=new StringBuffer();
		if(str1.equals("")&&str2.equals("")){
			
			return "�������󣬴�ͷ�ʹ�β���ܾ�Ϊ��";
		}
		for (int i = 0; i < words2.size(); i++) {
			len = words2.get(i).length();
			if (str1.length() > len || str2.length() > len)
				continue;
			if (str1.equals(words2.get(i).substring(0, str1.length()))
					&& str2.equals(words2.get(i).substring(len - str2.length(), len))) {// �뵥�ʵ�ǰstr1.length()λ�ͺ�str2.length()λ���бȽ�

				word.append(words2.get(i)+"\n");
				number++;
			}
		}
		word.append("��ͷΪ" + str1 + "��βΪ" + str2 + "����Ϊ" + number);
		return word.toString();

	}

}

