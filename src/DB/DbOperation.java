package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbOperation {
	private ResultSet rs=null;//���ز�ѯ�����
	private Connection con=null;// �������ݿ����
	private Statement stmt=null;// ����SQL�������
	public Connection getCon() {
		return con;
	}

	public Statement getStmt() {
		return stmt;
	}

	public ResultSet getRs() {
		return rs;
	}
	
	
	public ResultSet operation(String sql){
		String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL���ݿ�����

	       String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=WordDB";// ����Դ

	        try {

	            Class.forName(JDriver);// �������ݿ����棬���ظ����ַ���������

	        } catch (ClassNotFoundException e) {

	            // e.printStackTrace();
	            System.exit(0);

	       }

	        try {
	            String user = "sa";
	            String password = "123456";
	            con = DriverManager.getConnection(connectDB, user, password);// �������ݿ����
	            stmt = con.createStatement();// ����SQL�������
	            // ��ȡ����
	            rs = stmt.executeQuery(sql);// ����SQL����ѯ�����(����)
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("���ݿ����Ӵ���");
	            System.exit(0);
	        }
	        return rs;
	}
}
