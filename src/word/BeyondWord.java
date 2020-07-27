package word;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


import DB.DbOperation;
public class BeyondWord {
	@SuppressWarnings("rawtypes")
	private Map beyondword = new HashMap(); 
	@SuppressWarnings("rawtypes")
	private Map word=new HashMap();// 哈希表存储超纲单词及其数目
	@SuppressWarnings("rawtypes")
	public Map getBeyondword() {
		return beyondword;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getbeyondWord(String s1){
		WordNum wn = new WordNum(s1);
		wn.init();
		try {
			String sql=null;	
			DbOperation Db=new DbOperation();
			ResultSet rs=null;
			beyondword = wn.getmap();
			Set<?> mappings = beyondword.entrySet();
			// 通过映射后的set集合对元素进行遍历，得到key和value。
			Iterator<?> i = mappings.iterator();
			Map.Entry me = (Map.Entry) i.next();
			for (; i.hasNext();) {
				 me = (Map.Entry) i.next();
				String s = (String) me.getKey();
				sql= "SELECT word FROM AllWords where word=" +"'"+ s+"'";
				rs= Db.operation(sql);// 返回SQL语句查询结果集(集合)
				if(!rs.next()){
					 word.put(s, me.getValue());
				}
			}
			// 关闭连接
			Db.getStmt().close();// 关闭命令对象连接
			Db.getCon().close();// 关闭数据库连接
		} catch (SQLException e) {
			e.printStackTrace() ;
			System.out.println("数据库连接错误");
			System.exit(0);
		}
		return word;
	}
}
