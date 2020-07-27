package word;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import DB.DbOperation;

public class WordVary {
	private Map<String, Counter> hm = new HashMap<String, Counter>();// 存放单词及词频
	private StringBuffer ans=null;// 存放查询结果
	private List<String> ving=new ArrayList<String>();//保存动词现在分词
	private List<String> ved=new ArrayList<String>();//保存动词过去分词
	private List<String> ns=new ArrayList<String>();//保存名词复数
	private List<String> aer=new ArrayList<String>();//保存形容词比较级
	private List<String> aest=new ArrayList<String>();//保存形容词最高级
	public String VarySta(String path){
		/**
		 * 统计一个文档中各种词的变化形式的数目
		 */
		WordNum wn = new WordNum(path);
		wn.init();
		hm=wn.getmap();
		Iterator<String> it = hm.keySet().iterator();
		String word=null;
		while (it.hasNext()) {
			word=it.next();
			if(word.length()>4&&word.substring(word.length()-1).equals("s")){
				ns.add(word);
			}
			else if(word.length()>4&&word.substring(word.length()-2).equals("er")){
				aer.add(word);
			}
			else if(word.length()>4&&word.substring(word.length()-2).equals("ed")){
				ved.add(word);
			}
			else if(word.length()>4&&word.substring(word.length()-3).equals("est")){
				aest.add(word);
			}
			else if(word.length()>4&&word.substring(word.length()-3).equals("ing")){
				ving.add(word);
			}
			else{
				String sql=null;//="SELECT * FROM AllWords where word="+"'"+s+"'";
				DbOperation Db=new DbOperation();
			    ResultSet rs=null;// = Db.operation(sql);// 返回SQL语句查询结果集(集合)
				sql="SELECT * FROM irregular";
				rs=Db.operation(sql);
				try {
					String WP=null;
					String WPp=null;
					int i=0;//记录表里面的列数1-422列是动词，423-718是名词，719-727是比较级
					if(rs.next()){
						i=rs.getInt("id");
						WP=rs.getString(3).trim();
						WPp=rs.getString(4).trim();
		            	if(word.equals(WP)&&i>=1&&i<=422){
		            		ving.add(word);
		            	}
		            	else if(word.equals(WPp)&&i>=1&&i<=422){
		            		ved.add(word);
		            	}
		            	else if(word.equals(WP)&&i>=432&&i<=718){
		            		ns.add(word);
		            	}
		            	else if(word.equals(WP)&&i>=719&&i<=727){
		            		aer.add(word);
		            	}
		            	else if(word.equals(WPp)&&i>=719&&i<=727){
		            		aest.add(word);
		            	}
					} 
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		//将单词统计结果从链表放到字符串中
		
		ans=new StringBuffer("复数有"+ns.size()+"个\n"+"比较级有"+aer.size()+"个\n"
			+"最高级级有"+aest.size()+"个\n"+"动词现在分词有"+ving.size()+"个\n"+"动词过去分词有"+ved.size()+"个\n");
		int i=1;//标记形式
		bianli(ns,i);
		i++;
		bianli(aer,i);
		i++;
		bianli(aest,i);
		i++;
		bianli(ving,i);
		i++;
		bianli(ved,i);
		return ans.toString();
	}
	public void bianli(List<String> a,int i){
		Iterator<String> it1 = a.iterator();
		String key =null;
		while (it1.hasNext()) { // 迭代循环输出结果
			key = it1.next();
			switch(i){
			case 1:
				ans.append("复数形式："+key+"\n");
				break;
			case 2:
				ans.append("比较级："+key+"\n");
				break;
			case 3:
				ans.append("最高级："+key+"\n");
				break;
			case 4:
				ans.append("动词现在分词："+key+"\n");
				break;
			case 5:
				ans.append("动词过去分词："+key+"\n");
				break;
			default:
				ans.append(key+"\n");
			}
		}
	}
}
