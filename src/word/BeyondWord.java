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
	private Map word=new HashMap();// ��ϣ��洢���ٵ��ʼ�����Ŀ
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
			// ͨ��ӳ����set���϶�Ԫ�ؽ��б������õ�key��value��
			Iterator<?> i = mappings.iterator();
			Map.Entry me = (Map.Entry) i.next();
			for (; i.hasNext();) {
				 me = (Map.Entry) i.next();
				String s = (String) me.getKey();
				sql= "SELECT word FROM AllWords where word=" +"'"+ s+"'";
				rs= Db.operation(sql);// ����SQL����ѯ�����(����)
				if(!rs.next()){
					 word.put(s, me.getValue());
				}
			}
			// �ر�����
			Db.getStmt().close();// �ر������������
			Db.getCon().close();// �ر����ݿ�����
		} catch (SQLException e) {
			e.printStackTrace() ;
			System.out.println("���ݿ����Ӵ���");
			System.exit(0);
		}
		return word;
	}
}
