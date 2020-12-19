package tools;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
	
	private static final String JDBCPROPERTY = "jdbc.property";
	private static String DBDRIVER = "";
	private static String DBURL = "";
	private static String DBUSER = "";
	private static String PASSWORD = ""; 
	private Connection con;
	
	
	static {
		try {
			Properties property = new Properties();

			InputStream is = DatabaseConnection.class.getClassLoader()
								.getResourceAsStream("resource/"+JDBCPROPERTY);
			property.load(new InputStreamReader(is, "utf-8"));
			is.close();
			DBDRIVER = property.getProperty("DBDRIVER");
			DBURL = property.getProperty("DBURL");
			DBUSER = property.getProperty("DBUSER");
			PASSWORD = property.getProperty("PASSWORD");
			// ����������ֻ��ע��һ�ξ���
			Class.forName(DBDRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//���췽����ʵ��������ʱ�������Ӷ���
	public DatabaseConnection() {
		try {
			
			this.con = DriverManager.getConnection(DBURL, DBUSER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//ֱ�ӷ���ʵ��������ʱ���������Ӷ���
	public Connection getConnection() {
		return this.con;
	}
	//�ر����Ӷ���
	public void close() {
		if (this.con != null) {
			try {
				this.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
