package myframe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionOrcl {
	private String className = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.2.167:1521:orcl";
	private String user = "Java";
	private String password = "java";

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(this.className);
		Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
		return conn;
	}
}
