package word;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import DB.DbOperation;

public class FindWord {
	Map<String, String> hm = new HashMap<String, String>();
	public String find(String s){
		String means=null;//�洢���鵥����˼
		String sql="SELECT * FROM AllWords where word="+"'"+s+"'";
		DbOperation Db=new DbOperation();
	    ResultSet rs = Db.operation(sql);// ����SQL����ѯ�����(����)
	    try {
			if(rs.next()){
				means=rs.getString("means");
			}
			else{
				means="δ�ҵ��õ���";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	            // �ر�����
	    try {
			Db.getStmt().close();
			Db.getCon().close();// �ر����ݿ�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// �ر������������
		return means;
		/*
		String sql="SELECT * FROM AllWords";
		DbOperation Db=new DbOperation();
        ResultSet rs =Db.operation(sql);// ����SQL����ѯ�����(����)
        String a=null;//�ݴ��ȡ������
        //ѭ�����ÿһ����¼
        try {
			while (rs.next()) {
				a=rs.getString("word");
				a=a.trim();
				//System.out.println(a);
				//���ÿ���ֶ�
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
