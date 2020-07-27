package word;

import java.io.*;

import java.util.*;
import FileIO.FileOpen;

public class TiquWord {
	private List<String> words = new ArrayList<String>();// 存放所有单词
	private List<String> words2 = new ArrayList<String>();// 存放所有单词
	private Map<String, StringBuffer> lenWord = new HashMap<String, StringBuffer>();// 存放单词及长度
	private String openFile; // 打开文件名
	private BufferedReader br;// 输入流

	public TiquWord(String openFile) {
		super();
		this.openFile = openFile;
	}

	public void init() {
		try {
			words = Arrays.asList(new FileOpen().testFile(openFile));   //得到单词数组转为ArrayList

			for (int i = 0; i < words.size(); i++) {
				words2.add(words.get(i));// 对所有单词按长度进行分类，方便查询
			}		
			HashSet<String> hs = new HashSet<String>(words2); // 用于去除重复的单词，方便提取
			words2.clear();
			words2.addAll(hs);
			for (int i = 0; i < words2.size(); i++) { // 对所有单词按长度进行分类，方便查询
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
			return "长度为"+testLen+"的单词个数为"+l.length+"个\n"+lenWord.get(testLen + "").toString(); // 按逗号进行分割
			
		} else {
			
			return ("长度为" + testLen + "的单词个数为" + 0);
		}

	}

	public String htWord(String str1, String str2) {
		int number = 0;
		int len = 0;
		StringBuffer word=new StringBuffer();
		if(str1.equals("")&&str2.equals("")){
			
			return "输入有误，词头和词尾不能均为空";
		}
		for (int i = 0; i < words2.size(); i++) {
			len = words2.get(i).length();
			if (str1.length() > len || str2.length() > len)
				continue;
			if (str1.equals(words2.get(i).substring(0, str1.length()))
					&& str2.equals(words2.get(i).substring(len - str2.length(), len))) {// 与单词的前str1.length()位和后str2.length()位进行比较

				word.append(words2.get(i)+"\n");
				number++;
			}
		}
		word.append("词头为" + str1 + "词尾为" + str2 + "个数为" + number);
		return word.toString();

	}

}

