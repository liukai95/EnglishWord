package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbOperation {
	private ResultSet rs=null;//返回查询结果集
	private Connection con=null;// 连接数据库对象
	private Statement stmt=null;// 创建SQL命令对象
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
		String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎

	       String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=WordDB";// 数据源

	        try {

	            Class.forName(JDriver);// 加载数据库引擎，返回给定字符串名的类

	        } catch (ClassNotFoundException e) {

	            // e.printStackTrace();
	            System.exit(0);

	       }

	        try {
	            String user = "sa";
	            String password = "123456";
	            con = DriverManager.getConnection(connectDB, user, password);// 连接数据库对象
	            stmt = con.createStatement();// 创建SQL命令对象
	            // 读取数据
	            rs = stmt.executeQuery(sql);// 返回SQL语句查询结果集(集合)
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("数据库连接错误");
	            System.exit(0);
	        }
	        return rs;
	}
}
