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
	private Map<String, Counter> hm = new HashMap<String, Counter>();// ��ŵ��ʼ���Ƶ
	private StringBuffer ans=null;// ��Ų�ѯ���
	private List<String> ving=new ArrayList<String>();//���涯�����ڷִ�
	private List<String> ved=new ArrayList<String>();//���涯�ʹ�ȥ�ִ�
	private List<String> ns=new ArrayList<String>();//�������ʸ���
	private List<String> aer=new ArrayList<String>();//�������ݴʱȽϼ�
	private List<String> aest=new ArrayList<String>();//�������ݴ���߼�
	public String VarySta(String path){
		/**
		 * ͳ��һ���ĵ��и��ִʵı仯��ʽ����Ŀ
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
			    ResultSet rs=null;// = Db.operation(sql);// ����SQL����ѯ�����(����)
				sql="SELECT * FROM irregular";
				rs=Db.operation(sql);
				try {
					String WP=null;
					String WPp=null;
					int i=0;//��¼�����������1-422���Ƕ��ʣ�423-718�����ʣ�719-727�ǱȽϼ�
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
		//������ͳ�ƽ��������ŵ��ַ�����
		
		ans=new StringBuffer("������"+ns.size()+"��\n"+"�Ƚϼ���"+aer.size()+"��\n"
			+"��߼�����"+aest.size()+"��\n"+"�������ڷִ���"+ving.size()+"��\n"+"���ʹ�ȥ�ִ���"+ved.size()+"��\n");
		int i=1;//�����ʽ
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
		while (it1.hasNext()) { // ����ѭ��������
			key = it1.next();
			switch(i){
			case 1:
				ans.append("������ʽ��"+key+"\n");
				break;
			case 2:
				ans.append("�Ƚϼ���"+key+"\n");
				break;
			case 3:
				ans.append("��߼���"+key+"\n");
				break;
			case 4:
				ans.append("�������ڷִʣ�"+key+"\n");
				break;
			case 5:
				ans.append("���ʹ�ȥ�ִʣ�"+key+"\n");
				break;
			default:
				ans.append(key+"\n");
			}
		}
	}
}
