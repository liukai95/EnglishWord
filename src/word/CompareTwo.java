package word;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CompareTwo {
	String s1=null;//文档1地址
	String s2=null;//文档2地址
	int count1;//文档1的单词个数
	int count2;//文档2的单词个数
	@SuppressWarnings("rawtypes")
	Map sameWords =new HashMap ();//两篇文档公共单词
	//StringBuffer sameWords=null;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map Compare(String s1,String s2) throws IOException{
		WordNum wn = new WordNum(s1);
		wn.init();
		Map wend1=wn.getmap();
		WordNum wn2 = new WordNum(s2);
		wn2.init();
		Map wend2=wn2.getmap();
		count1=wend1.size();
		count2=wend2.size();
		System.out.println("文档1词汇量："+count1);
		System.out.println("文档2词汇量："+count2);
		Set<?> map1 = wend1.entrySet(); 
		Set<?> map2 = wend2.entrySet(); 
		//通过映射后的set集合对元素进行遍历，得到key和value。 
	    for (Iterator<?> i = map1.iterator(); i.hasNext();) { 
	    	Map.Entry m1 = (Map.Entry)i.next();
	    	Iterator<?> j= map2.iterator();
	    	 for ( ; j.hasNext();){
	    		 Map.Entry m2 = (Map.Entry)j.next();
	    		 if(m1.getKey().equals(m2.getKey())){
	    			 //sameWords.append(((String) m2.getKey())+":"+m2.getValue()+"\n");
	    			 sameWords.put((String) m2.getKey(), m2.getValue());
	    		 }
	    	 }
	      } 
	    return sameWords;
	}
}
