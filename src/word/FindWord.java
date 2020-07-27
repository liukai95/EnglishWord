package word;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import DB.DbOperation;

public class FindWord {
	Map<String, String> hm = new HashMap<String, String>();
	public String find(String s){
		String means=null;//存储待查单词意思
		String sql="SELECT * FROM AllWords where word="+"'"+s+"'";
		DbOperation Db=new DbOperation();
	    ResultSet rs = Db.operation(sql);// 返回SQL语句查询结果集(集合)
	    try {
			if(rs.next()){
				means=rs.getString("means");
			}
			else{
				means="未找到该单词";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	            // 关闭连接
	    try {
			Db.getStmt().close();
			Db.getCon().close();// 关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 关闭命令对象连接
		return means;
		/*
		String sql="SELECT * FROM AllWords";
		DbOperation Db=new DbOperation();
        ResultSet rs =Db.operation(sql);// 返回SQL语句查询结果集(集合)
        String a=null;//暂存读取的数据
        //循环输出每一条记录
        try {
			while (rs.next()) {
				a=rs.getString("word");
				a=a.trim();
				//System.out.println(a);
				//输出每个字段
			if(a.substring(0, s.length()).equals(s)){
				means=rs.getString("means");
				hm.put(a, means);
				//System.out.println(a+means);
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return hm;*/
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new findWord().find("hello"));
	}*/

}
